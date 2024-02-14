# TEST INTEGRATION --> CASQUETTE TESTEUR
Feature: Hello Halim

  Background:
    Given url baseUrl //defined in karate-config.js

  @actuator
  Scenario: Halim Actuator
    Given path '/actuator'
    When method GET
    Then status 200
    And print response
    And match $._links.health.href contains "health"
    And match $._links.info.href contains "info"

  @actuator
  Scenario: Halim health
    Given path '/actuator/health'
    When method GET
    Then status 200
    And print response
    And match $.status == "UP"

  @actuator
  Scenario: Halim info
    Given path '/actuator/info'
    When method GET
    Then status 200
    And print response
    And match $.build.name == 'rest_service'
    And match $.build.version == '#notnull'

  @api
  Scenario: Halim hello
    Given path '/api/hello'
    When method GET
    Then status 200
    And match $ contains 'Hello World!'

  @api
  Scenario: Halim bad hello
    Given path '/api/hello2'
    When method GET
    Then status 404

  @api
  Scenario Outline: Halim greet user
    Given path '/api/hello'
    And params {name: '<user>'}
    When method GET
    Then status 200
    And match $ contains 'Hello <user>!'

    Examples:
      | user    |
      | Halim   |
      | Agnès   |
      | Thierry |
      | Elham   |

  @api
  Scenario: Halim hello advanced
    Given path '/api/hello/advanced'
    When method GET
    Then status 200
    And match $ contains 'Bonjour :)!'

  @api
  Scenario: Halim hello bad advanced
    Given path '/api/hello/advancedd'
    When method GET
    Then status 404

  @api
  Scenario Outline:  Halim hello with language and name
    Given path '/api/hello/advanced'
    And params {lang: '<lang>', name:'<user>'}
    When method GET
    Then status 200
    And match $ contains '<greeting> <name>!'

    Examples:
      | lang | greeting| user  | name  |
      | fr   | Bonjour | Halim | Halim |
      | en   | Hello   | Elham | Elham |
      |      | Bonjour | Halim | Halim |
      |      | Bonjour |       | :)    |
      | en   | Hello   |       | :)    |
      | fr   | Bonjour |       | :)    |

  @api
  Scenario Outline: Halim hello with bad language
    Given path '/api/hello/advanced'
    And params {lang: '<lang>', name:'<user>'}
    When method GET
    Then status 200
    And match $ contains "Error, this language doesn't exist !"

    Examples:
      | lang |  user  |
      | it   |  Halim |
      | it   |        |