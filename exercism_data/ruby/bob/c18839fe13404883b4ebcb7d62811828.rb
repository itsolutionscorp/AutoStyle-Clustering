class Bob
  def hey(phrase)
    # Unsure on this style. It felt like a monkey patch but I know that's evil.
    # Is this a reasonable alternative? Where are my refinements!?
    phrase = BobString.new(phrase)

    return "Sure." if phrase.question?
    return "Fine. Be that way." if phrase.silent?
    return "Woah, chill out!" if phrase.shouting?
    return "Whatever."
  end
end

class BobString < String
  def question?
    self.end_with?('?')
  end

  def shouting?
    self.upcase == self
  end

  def silent?
    self.empty?
  end
end
