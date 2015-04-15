class Bob

  attr_reader :message

  def hey message

    @message = message.to_s   
    case
      when silence?  then 'Fine. Be that way.'                      
      when question? then 'Sure.'      
      when shout? then 'Woah, chill out!'
      else 'Whatever.'  
    end  
  end  

  private
    def shout?
      @message == @message.upcase
    end 
    def question?        
      @message.end_with? "?"
    end 
    def silence?
      @message.nil? || @message.empty? 
    end 
	 
end	
