class Bob


  def hey sentence
    if silent? sentence
      'Fine. Be that way!' 
    elsif  shouting? sentence
       'Woah, chill out!' 
    elsif question? sentence
      "Sure."
    else 
      'Whatever.'
    end
  end

  def silent?  sentence
     sentence.nil? || sentence.empty?
  end

  def shouting? sentence 
    sentence == sentence.upcase    
  end

  def question? sentence
    sentence.end_with?('?') 
  end

end
