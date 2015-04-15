class Bob
  def hey(input)
    statement = Statement.new(input)
    return 'Fine. Be that way.' if statement.is_silent
    return "Sure." if statement.is_question
    return "Whatever." if statement.is_not_yelling
    return "Woah, chill out!"
  end
end

class Statement
  attr_reader :statement

  def initialize(statement)
    @statement = statement
  end

  def is_silent
    !statement || statement.empty?
  end

  def is_question
    statement.end_with? '?'
  end

  def is_not_yelling
    statement != statement.upcase
  end
end
