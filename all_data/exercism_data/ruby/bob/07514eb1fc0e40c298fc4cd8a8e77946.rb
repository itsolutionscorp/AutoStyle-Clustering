class Sentence
  def initialize(sentence)
    @sentence = sentence
  end

  def to_s
    @sentence
  end

  def silence?
    @sentence.strip == ""
  end

  def shout?
    norm = @sentence.gsub(/[^[:alpha:]]/, "")
    norm != "" && norm.upcase == norm
  end

  def question?
    @sentence.strip[-1] == "?"
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
