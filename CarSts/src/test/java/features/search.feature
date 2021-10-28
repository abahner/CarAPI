Feature: testing search functionality

  Scenario Outline: user inputs every combination of search paramaters both valid/invalid and the correct result is returned
    Given the browser is open
    And the user is on the home page
    When keys are input in the fields <make>, <model> and <trim>
    And the user sends the query
    Then the correct results are returned

    Examples: 
      | make | model   | trim |
      | Ford |         |      |
      | Ford | Fusion  |      |
      | Ford | Fusion  | SE   |
      | Ford |         | SE   |
      |      | Mustang |      |
      |      |         | RS   |
      |      | Mustang | RS   |
      | Ford | Mustang | SE   |
      | Fod  |         | RS   |
