package hexlet.code.repository;

import hexlet.code.model.UrlCheck;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlCheckRepository extends BaseRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlCheckRepository.class);
    public static void saveUrlCheck(UrlCheck urlCheck) throws SQLException {
        String sql = "INSERT INTO url_checks "
                + "(url_id, status_code, title, h1, description, created_at) VALUES (?, ?, ?, ?, ?, ?)";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setLong(1, urlCheck.getUrlId());
            preparedStatement.setInt(2, urlCheck.getStatusCode());
            preparedStatement.setString(3, urlCheck.getTitle());
            preparedStatement.setString(4, urlCheck.getH1());
            preparedStatement.setString(5, urlCheck.getDescription());
            var createdAt = LocalDateTime.now();
            preparedStatement.setTimestamp(6, Timestamp.valueOf(createdAt));

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                urlCheck.setId(generatedKeys.getLong(1));
                urlCheck.setCreatedAt(createdAt);
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }

    public static List<UrlCheck> findAllChecksByUrlId(Long urlId) throws SQLException {
        List<UrlCheck> checks = new ArrayList<>();
        String sql = "SELECT * FROM url_checks WHERE url_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, urlId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                UrlCheck check = new UrlCheck();
                check.setId(rs.getLong("id"));
                check.setUrlId(rs.getLong("url_id"));
                check.setStatusCode(rs.getInt("status_code"));
                check.setTitle(rs.getString("title"));
                check.setH1(rs.getString("h1"));
                check.setDescription(rs.getString("description"));
                check.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

                checks.add(check);
            }
        }
        return checks;
    }

    public static Optional<UrlCheck> findLastCheckByUrlId(Long urlId) throws SQLException {
        String sql = "SELECT * FROM url_checks WHERE url_id = ? ORDER BY created_at DESC LIMIT 1";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, urlId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                UrlCheck check = new UrlCheck();
                check.setId(rs.getLong("id"));
                check.setUrlId(rs.getLong("url_id"));
                check.setStatusCode(rs.getInt("status_code"));
                check.setTitle(rs.getString("title"));
                check.setH1(rs.getString("h1"));
                check.setDescription(rs.getString("description"));
                check.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

                return Optional.of(check);
            }
        }

        return Optional.empty();
    }

    public static Map<Long, UrlCheck> getLastChecksForAllUrls() throws SQLException {
        LOGGER.info("Getting last checks for all URLs");
        String sql = "SELECT DISTINCT ON (url_id) * FROM url_checks ORDER BY url_id, created_at DESC";
        var lastChecks = new HashMap<Long, UrlCheck>();
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                var listOfUrls = new UrlCheck();
                listOfUrls.setId(resultSet.getLong("id"));
                listOfUrls.setStatusCode(resultSet.getInt("status_code"));
                listOfUrls.setTitle(resultSet.getString("title"));
                listOfUrls.setH1(resultSet.getString("h1"));
                listOfUrls.setDescription(resultSet.getString("description"));
                listOfUrls.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                var urlId = resultSet.getLong("url_id");
                listOfUrls.setUrlId(urlId);
                lastChecks.put(urlId, listOfUrls);
            }
            return lastChecks;
        } catch (SQLException e) {
            LOGGER.error("Failed to get last checks for all URLs", e);
            throw e;
        }
    }
}
