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
    if string.blank?
      "Fine. Be that way!"
    elsif string.all_caps?
      "Woah, chill out!"
    elsif string.question?
      "Sure."
    else
      "Whatever."
    end
  end

end
