class Bob
  
  def hey(sentence_to_check)    
    if silence?(sentence_to_check)
      "Fine. Be that way."    
    elsif a_question?(sentence_to_check)
      "Sure."    
    elsif shouting?(sentence_to_check)
      "Woah, chill out!" 
    else
      "Whatever."
    end
  end
  
  protected
  

  def silence?(string_to_check)
    string_to_check.nil? || "" == string_to_check
  end
  

  def a_question?(string_to_check)
    string_to_check.end_with?("?")
  end
  

  def shouting?(string_to_check)
    string_to_check.upcase == string_to_check
  end  
end
