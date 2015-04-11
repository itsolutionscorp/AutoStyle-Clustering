class String

  def blank?
    self.to_s.strip == ""
  end

  def question?
    self.end_with? "?"
  end

  def all_caps?
    self == self.upcase
  end

end

class Bob

  def hey(string)
    string = string.to_s # sanity check to make sure we have one
    return "Fine. Be that way!" if string.blank?
    return "Woah, chill out!"   if string.all_caps?
    return "Sure."              if string.question?
    "Whatever."
  end

end
