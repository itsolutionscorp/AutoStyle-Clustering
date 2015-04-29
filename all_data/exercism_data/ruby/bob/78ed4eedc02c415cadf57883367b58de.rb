class Bob
  def hey(input)
    input = Input.new(input)

    return "Fine. Be that way." if input.empty?
    return "Sure." if input.question?
    return "Woah, chill out!" if input.shouting?

    "Whatever."
  end
end

class Input
  attr_reader :string

  def initialize(string)
    @string = String(string)
  end

  def empty?
    string == ""
  end

  def question?
    string.end_with?('?')
  end

  def shouting?
    !empty? && string == string.upcase
  end
end
