class Utterance
  def initialize phrase
    @utterance = phrase
  end
  
  def shout? 
    @utterance == @utterance.upcase
  end

  def silence? 
    @utterance.strip.empty?
  end

  def question? 
    @utterance.end_with? "?"
  end
end

class Bob
  def hey phrase
    utterance = Utterance.new phrase
    return "Fine. Be that way!" if utterance.silence? 
    return "Woah, chill out!"   if utterance.shout?
    return "Sure."              if utterance.question?
    "Whatever."
  end
end
  
