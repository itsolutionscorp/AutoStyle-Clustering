class String
  def silence?
    self.strip == ""
  end

  def shout?
    norm = self.gsub(/[^[:alpha:]]/, "")
    norm != "" && norm.upcase == norm
  end

  def question?
    self.strip[-1] == "?"
  end
end

class Bob
  def hey(str)
    res ||= "Fine. Be that way!" if str.silence?
    res ||= "Whoa, chill out!"   if str.shout?
    res ||= "Sure."              if str.question?
    res ||= "Whatever."
  end
end
