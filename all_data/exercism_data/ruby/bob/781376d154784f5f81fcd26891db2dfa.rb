class Bob
  def hey( something )
    return "Fine. Be that way!" if something.blank?
    return "Woah, chill out!" if something.shouting?
    return "Sure." if something.question?
    "Whatever."
  end
end

class String
  def shouting?
    self == self.upcase
  end

  def question?
    self[-1] == "?"
  end

  def blank?
    self.gsub(/\s+/,"") == ""
  end
end
