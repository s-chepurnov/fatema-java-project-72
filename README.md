### Hexlet tests and linter status:
[![Actions Status](https://github.com/F-Jahura/java-project-72/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/F-Jahura/java-project-72/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=F-Jahura_java-project-72&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=F-Jahura_java-project-72)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=F-Jahura_java-project-72&metric=coverage)](https://sonarcloud.io/summary/new_code?id=F-Jahura_java-project-72)
[deploy app](https://java-project-72-2-c7vd.onrender.com)

# Проект "Анализатор страниц"

**Описание:**

Проект представляет собой web-приложение для анализа сайтов на SEO-пригодность.

**Сборка:**
```java
make build
```
**Тест:**
```java
make test
```
**Sonar:**
```java
make sonar
```
**CheckStyle:**
```java
make lint
```
**Запуск:**
```java
make run
```
**Приложение доступно по адресу:**  http://localhost:7070

**Главная страница**
     - Поле для ввода URL сайта для проверки
     - Кнопка "Check" для запуска анализа

**Добавление сайта
     - После успешного добавления сайта отображается сообщение подтверждения  
     - Сайт добавляется в общий список 

**Детальная информация о сайте
     - Основная информация о сайте (ID, URL, дата создания)
     - Возможность запустить проверку вручную
     - Результаты последней проверки:
          - Код ответа сервера (Answer code)
          - Заголовок страницы (Title)
          - Заголовок (H1)
          - Описание (Description)
          - Дата и время проверки (Check date)
