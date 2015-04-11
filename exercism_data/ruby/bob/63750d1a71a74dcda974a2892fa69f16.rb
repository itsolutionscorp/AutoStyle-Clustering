class Bob
  def hey(input)
    input_parser = InputParser.new(input)
    if input_parser.nothing?
      "Fine. Be that way!"
    elsif input_parser.shouting?
      "Woah, chill out!"
    elsif input_parser.question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class InputParser
  def initialize(input)
    @input = input
  end

  def nothing?
    input.strip == ""
  end

  def shouting?
    input.match(/[A-Za-z]/) && input == input.upcase
  end

  def question?
    input.end_with?("?")
  end

private
  attr_reader :input
end
