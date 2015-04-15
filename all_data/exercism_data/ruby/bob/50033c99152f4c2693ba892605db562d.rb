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
  attr_reader :input

  def initialize(input)
    @input = input
  end

  def is_silent
    !input || input.empty?
  end

  def is_question
    input.end_with? '?'
  end

  def is_not_yelling
    input != input.upcase
  end
end
