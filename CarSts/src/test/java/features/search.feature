Feature: testing search functionality

  Scenario Outline: user inputs every combination of search paramaters both valid/invalid and the correct result is returned
    Given the browser is open
    And the user is on the home page
    When keys are input in the fields <make>, <model>, <trim>, <year>, <price> and <mileage>
    And the user sends the query
    Then the correct results are returned

    Examples: 
      | make    | model    | trim  | year | price | mileage |
      | Ford    |          |       |      |       |         |
      | Ford    | Fusion   |       |      |       |         |
      | Ford    | Fusion   | SE    |      |       |         |
      | Ford    | Fusion   | SE    | 2012 |       |         |
      | Ford    | Fusion   | SE    | 2012 |   900 |         |
      | Ford    | Fusion   | SE    | 2012 |   900 |  245000 |
      |         | Mustang  |       |      |       |         |
      |         | Mustang  | RT    |      |       |         |
      |         | Mustang  | RT    | 2015 |       |         |
      |         | Mustang  | RT    | 2015 | 14000 |         |
      |         | Mustang  | RT    | 2015 | 14000 |   34000 |
      |         |          | WS6   |      |       |         |
      |         |          | WS6   | 2000 |       |         |
      |         |          | WS6   | 2000 |  2000 |  176000 |
      |         |          |       | 1969 |       |         |
      |         |          |       | 1969 | 96000 |         |
      |         |          |       | 1969 | 96000 |   68000 |
      |         |          |       |      | 45000 |         |
      |         |          |       |      | 45000 |   12000 |
      |         |          |       |      |       |  245000 |
      | Ford    | Mustang  | RT    | 2015 |       |   34000 |
      | Ford    | Mustang  | RT    |      | 14000 |   34000 |
      | Ford    | Mustang  |       | 2015 | 14000 |   34000 |
      | Ford    |          | RT    | 2015 | 14000 |   34000 |
      | Ford    |          |       | 2012 |   900 |  245000 |
      | Ford    |          |       |      | 14000 |   34000 |
      | Pontiac |          |       |      |       |  176000 |
      |         | Camaro   |       |      | 45000 |   12000 |
      |         | Camaro   |       |      |       |   12000 |
      |         |          | Judge |      | 96000 |   68000 |
      |         |          | Judge |      |       |   68000 |
      |         |          |       | 2000 |       |  176000 |
      |         | Camaro   | ZL1   |      | 45000 |   12000 |
      |         | Trans Am | WS6   |      |       |  176000 |
      |         | Fusion   | SE    | 2012 |       |  245000 |
      |         |          | RT    | 2015 |       |   34000 |
