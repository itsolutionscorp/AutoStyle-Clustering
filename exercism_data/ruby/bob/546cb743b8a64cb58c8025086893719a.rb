class Bob

  attr_reader :message

  def hey message

    @message = message
    
    case message 
      when silence? then 'Fine. Be that way.'           
      when more_silence? then 'Fine. Be that way.'                 
      when shouting? then 'Woah, chill out!'
      when question? then 'Sure.'      
      else 'Whatever.'  
    end  
  end  

  def shouting?
    @message.upcase
  end 
  def question?        
    @message.end_with?("?")
  end 
  def silence?
    nil 
  end 
  def more_silence?
    '' 
  end 
	 
  private :question?,:shouting?,:silence?,:more_silence?
end	
