#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Flujo para buscar un reloj
  Como: Cliente
  Quiero: buscar un reloj
  Para: Comprarlo


  @reloj
  Scenario: Buscar un reloj
    Given inicia reporte, flujo: "Cliente busca reloj"
    And ingreso a mercado libre
    When realiza buqueda "reloj casio"
    Then muestra relojes
    And selecciona un producto
    And finaliza reporte







