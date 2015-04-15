class NilClass
  def blank?
    self.nil?
  end
end

class String
  def blank?
    self == '' || self.nil?
  end

  def question?
    self.end_with? '?'
  end

  def shouting?
    self == self.upcase
  end
end

class Bob
  def hey(message)
    return "Fine. Be that way!" if message.blank?
    return "Woah, chill out!" if message.shouting?
    return "Sure." if message.question?

    "Whatever."
  end
end
