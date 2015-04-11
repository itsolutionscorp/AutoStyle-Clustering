class Phrase
  def initialize(phrase)
    @phrase = phrase
  end
  
  def response
    if silence?
      "Fine. Be that way."
    elsif shouting?
      "Woah, chill out!"
    elsif question?
      "Sure."
    else 
      "Whatever."
    end
  end
  
  private
  
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
  def hey(phrase_words)
    Phrase.new(phrase_words).response
  end
end
