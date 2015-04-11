class Bob
  def hey phrase
    msg = Phrase.new(phrase)
    if msg.multiline?
      "Whatever."
    elsif msg.silent?
      "Fine. Be that way!"
    elsif msg.asking? || msg.numeric_question?
      "Sure."
    elsif msg.stating? || msg.forceful? || msg.acro_in_reg? || msg.only_numbers?
      "Whatever."
    elsif msg.shouting?
      "Woah, chill out!"
    end
  end
end

class Phrase < String
  def multiline?
    self.match(/\n/)
  end

  def silent?
    !self.match(/[a-zA-Z]/) && !self.match(/\d/)
  end

  def stating?
    self.match(/\.$/)
  end

  def shouting?
    self.upcase == self
  end

  def asking?
    if !self.match(/[a-zA-Z]/)
      self.match(/\?$/)
    else
      self.match(/\?$/) && !self.shouting?
    end
  end

  def numeric_question?
    self.asking? && self.match(/\d+/)
  end

  def forceful?
    self.match(/\!$/) && !self.shouting?
  end

  def acro_in_reg?
    self.match(/[A-Z]{2,}/) && self.stating?
  end

  def only_numbers?
    !self.asking? && !self.match(/[a-zA-Z]/)
  end
end
