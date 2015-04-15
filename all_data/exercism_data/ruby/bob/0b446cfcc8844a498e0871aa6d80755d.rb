class Bob
  def hey(text)
    greeting = Greeting.new(text)
    if greeting.is_silent?
      "Fine. Be that way!"
    elsif greeting.is_shouted?
      "Woah, chill out!"
    elsif greeting.is_question?
      "Sure."
    else
      "Whatever."
    end
  end
end

class Greeting
  attr_reader :text
  def initialize(text)
    @text = text
  end

  def is_question?
    text.end_with? "?"
  end

  def is_silent?
    text.lstrip.empty?
  end

  def is_shouted?
    text.upcase == text
  end
end
