
Feature: As a librarian, I want to know borrowed books number

  @db @ui @wip
  Scenario: verify the total amount of borrowed books
    Given the "librarian" on the home page. DM
    When the librarian gets borrowed books number. DM
    Then borrowed books number information must match with DB. DM