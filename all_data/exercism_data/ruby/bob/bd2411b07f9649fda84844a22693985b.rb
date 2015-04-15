class Bob
  attr_reader :sentence

  def hey sentence
    @sentence = sentence
    if silent
      'Fine. Be that way!' 
    elsif  shouting
       'Woah, chill out!' 
    elsif question
      "Sure."
    else 
      'Whatever.'
    end
  end

  def silent 
     sentence.nil? || sentence.empty?
  end

  def shouting 
    sentence == sentence.upcase    
  end

  def question 
    sentence.end_with?('?') 
  end
end
