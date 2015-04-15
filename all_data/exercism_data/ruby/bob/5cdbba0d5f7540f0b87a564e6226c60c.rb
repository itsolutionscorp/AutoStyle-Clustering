class String
  def upcase?
    self == self.upcase
  end

  def shouting? 
    /[A-Z]/.match(self) && upcase?
  end

  def question? 
    self.split(//).last == "?"
  end

  def silence? 
    self.strip.empty?
  end
end

class Bob
  def hey remark
    return "Whoa, chill out!" if remark.shouting?
    return "Sure." if remark.question?
    return "Fine. Be that way!" if remark.silence?
    "Whatever."
  end
end
