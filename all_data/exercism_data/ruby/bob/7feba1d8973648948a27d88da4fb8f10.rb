class Bob
  attr_reader :heard

  def hey(heard)
    @heard = heard
    
    response_to_silence || response_to_shouting || response_to_questions || "Whatever."
  end

  private

  def response_to_silence
    silence? && "Fine. Be that way!"
  end
  
  def response_to_shouting
    shouting? && "Woah, chill out!"
  end
  
  def response_to_questions
    question? && "Sure."
  end  

  def silence?
    heard.to_s.strip == ""
  end

  def shouting?
    heard == heard.upcase
  end

  def question?
    heard.end_with?("?")
  end
end
