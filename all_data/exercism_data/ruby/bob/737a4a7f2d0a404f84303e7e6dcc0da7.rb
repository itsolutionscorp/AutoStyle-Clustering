class Bob
  def hey(statement)
    phrase = Phrase.new(statement)

    return "Fine. Be that way." if phrase.blank?
    return "Woah, chill out!" if phrase.uppercase?
    return "Sure." if phrase.question?
    "Whatever."
  end

end

class Phrase < String

  def initialize(phrase)
    super(phrase || "")
  end

  def uppercase?
    self == self.upcase
  end

  def question?
    self.end_with?("?")
  end

  def blank?
    self.empty?
  end
end
