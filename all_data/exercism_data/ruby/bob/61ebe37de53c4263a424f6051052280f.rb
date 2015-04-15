class Bob
  def hey(statement)
    phrase = Phrase.new(statement)

    return "Fine. Be that way." if phrase.say_nothing?
    return "Woah, chill out!" if phrase.yelling?
    return "Sure." if phrase.question?
    "Whatever."
  end

end

class Phrase < String

  def initialize(phrase)
    super(phrase || "")
  end

  def yelling?
    self == self.upcase
  end

  def question?
    self.end_with?("?")
  end

  def say_nothing?
    self.empty?
  end
end
