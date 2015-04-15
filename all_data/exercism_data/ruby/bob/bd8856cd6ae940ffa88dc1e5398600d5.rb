class Utterance
  def initialize(string)
    @utterance = string.to_s
  end
  
  def question?
    !yell? && @utterance.match(/\?\Z/)
  end

  def yell?
    @utterance.match(/[A-Z]/) && !@utterance.match(/[a-z]/)
  end

  def silent?
    @utterance == ''
  end
end

class Bob
  def hey(string)
    u = Utterance.new(string)
    return "Sure."              if u.question?
    return "Woah, chill out!"   if u.yell?
    return "Fine. Be that way!" if u.silent?
    return "Whatever."
  end
end
