class Bob

  def hey(phrase)
    phrase.extend Phrase
    return "Woah, chill out!" if phrase.shouting?
    return "Sure." if phrase.question?
    return "Fine. Be that way!" if phrase.silent?
    return "Whatever."
  end

end


module Phrase

  def shouting?
    self.upcase==self && self.match(/[A-Za-z]/)
  end

  def question?
    self.match /\?\z/
  end

  def silent?
    self.empty? || self.match(/^\s+$/)
  end

end
