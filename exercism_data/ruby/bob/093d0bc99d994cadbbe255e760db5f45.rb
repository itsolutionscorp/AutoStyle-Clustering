class Phrase
  def initialize(phrase)
    @phrase = phrase
  end
  
  def shouting?
    @phrase.upcase == @phrase
  end
  
  def question?
    @phrase.end_with?("?")
  end
  
  def silence?
    @phrase.nil? || @phrase.empty?
  end
end

class Bob
  def hey(sentence)
    response_to(Phrase.new(sentence))
  end
  
  private
  
  def response_to(phrase)
    if phrase.silence?
      "Fine. Be that way."
    elsif phrase.shouting?
      "Woah, chill out!"
    elsif phrase.question?
      "Sure."
    else 
      "Whatever."
    end
  end
end
