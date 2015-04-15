class Bob; 
  def hey(text_in)                                               
    return "Fine. Be that way!" if isSilent?(text_in)   
    return "Woah, chill out!" if isYelling?(text_in)   
    return "Sure." if isQuestion?(text_in)  
    return "Whatever."
  end            
  
  private 
  
  def isSilent?(text)
    return true if text.nil? or text.strip == ''  
    return false
  end
  
  def isYelling?(text)
    return true if text == text.upcase
    return false   
  end           
  
  def isQuestion?(text)
    return true if text.end_with?('?') 
    return false     
  end
end
