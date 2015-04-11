class Bob
  def hey(remark)
    if remark.yell?
      return "Whoa, chill out!"
    elsif remark.question?
      return "Sure."
    elsif remark.silence?
      return "Fine. Be that way!"
    else
      return "Whatever."
    end
  end
  
  
end

class String
  def yell?
    (self.upcase == self) && !(self.match(/[A-Z]/).nil?) && (self != "I?")
  end
  def question?
    self.split(//).last == "?"
  end
  def silence?
    self.sub(/\s+/,"") == ""
  end
end
