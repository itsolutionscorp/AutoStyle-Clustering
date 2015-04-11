class Bob
  def hey(text)         
    @statement = Statement.new(text)                        
    return "Fine. Be that way!" if @statement.silent?   
    return "Woah, chill out!" if @statement.yelling?  
    return "Sure." if @statement.question?
    return "Whatever."
  end            
end

class Statement   
  def initialize(text)
    @text = text  
  end    
 
  def silent?
    (@text.nil? or @text.strip.empty?) 
  end
  
  def yelling?
    @text == @text.upcase
  end           
  
  def   question?
    @text.end_with?('?')      
  end
end
