class Bob
  def hey(text)         
    @statement = Statement.new(text)                        
    return "Fine. Be that way!" if @statement.is_silent?   
    return "Woah, chill out!" if @statement.is_yelling?  
    return "Sure." if @statement.is_question?
    return "Whatever."
  end            
end

class Statement   
  def initialize(text)
    @text = text  
  end    
 
  def is_silent?
    (@text.nil? or @text.strip == '') 
  end
  
  def is_yelling?
    @text == @text.upcase
  end           
  
  def is_question?
    @text.end_with?('?')      
  end
end
