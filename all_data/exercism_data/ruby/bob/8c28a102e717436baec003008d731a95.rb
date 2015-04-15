class Bob; 
  def hey(text_in)                                               
    return "Fine. Be that way!" if is_silent?(text_in)   
    return "Woah, chill out!" if is_yelling?(text_in)   
    return "Sure." if is_question?(text_in)  
    return "Whatever."
  end            
  
  private 
  
  def is_silent?(text)
    return (text.nil? or text.strip == '') 
  end
  
  def is_yelling?(text)
    text == text.upcase
  end           
  
  def is_question?(text)
    return text.end_with?('?')      
  end
end
