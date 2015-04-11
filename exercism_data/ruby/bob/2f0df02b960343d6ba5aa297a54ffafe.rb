class Bob

  def hey message
    case
      when silence?(message)  then 'Fine. Be that way!'                              
      when shout?(message) then 'Woah, chill out!'
      when question?(message) then 'Sure.'    
      else 'Whatever.'  
    end  
  end  

  private
    def shout? message
      message == message.upcase
    end 
    def question? message       
      message.end_with? "?"
    end 
    def silence? message
      message.to_s.strip == ""
    end 

end 
