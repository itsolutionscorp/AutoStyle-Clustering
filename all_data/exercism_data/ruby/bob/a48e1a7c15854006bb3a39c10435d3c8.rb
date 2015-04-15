class String
  def upcase?
   self == upcase
  end

  def last
    split(//).last
  end

  def shouting? 
    /[A-Z]/.match(self) && upcase?
  end

  def question? 
    last == "?"
  end

  def silence? 
    strip.empty?
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
