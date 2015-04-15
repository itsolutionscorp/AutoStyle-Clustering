class Bob
  def hey(input)
    statement = Statement.new(input)
    return 'Fine. Be that way.' if statement.silent?
    return "Sure." if statement.question?
    return "Whatever." unless statement.yelling?
    return "Woah, chill out!"
  end
end

class Statement
  attr_reader :input

  def initialize(input)
    @input = input
  end

  def silent?
    !input || input.empty?
  end

  def question?
    input.end_with? '?'
  end

  def yelling?
    input == input.upcase
  end
end
