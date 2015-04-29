class Sentence < String
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
    snt = Sentence.new str
    res ||= "Fine. Be that way!" if snt.silence?
    res ||= "Whoa, chill out!"   if snt.shout?
    res ||= "Sure."              if snt.question?
    res ||= "Whatever."
  end
end
