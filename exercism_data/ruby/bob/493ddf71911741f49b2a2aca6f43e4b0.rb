class Bob
  def hey str
    @str = str
    if shouting? 
      'Woah, chill out!'
    elsif questioned?
      'Sure.'  
    else
      'Whatever.'
    end
  end
  
  def shouting?
    if @str == @str.upcase #&& (!questioned?) && (!talked_forcefully_to?)
      true
    else
      false  
    end  
  end
  
  def questioned?
    if @str[-1] == "?"
      true
    else
      false
    end    
  end 
  
  def talked_forcefully_to?
    if @str[-1] == "!"
      true
    else
      false
    end  
  end
  
end
