class Bob
  def hey string, evaluater = Evaluate.new(string)
    response = "Whatever."
    if evaluater.keeps_silent?
      response = "Fine. Be that way!"
    elsif evaluater.shouts?
      response = "Woah, chill out!"
    elsif evaluater.is_question?
      response = "Sure."
    end

    response
  end
end

class Evaluate
  attr_reader :string

  def initialize(string)
    @string = string
  end

  def shouts?
    string == string.upcase
  end

  def keeps_silent?
    string.strip.empty?
  end

  def is_question?
    string.end_with? "?"
  end
end
