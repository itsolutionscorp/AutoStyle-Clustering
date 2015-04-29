class Bob
  
  def hey message
    Exchange.new(message).response
  end
  
  class Exchange
    
    def initialize message
      @message = message.strip
    end
    
    def response
      case
      when shouted_at? then "Woah, chill out!"
      when asked_a_question? then "Sure."
      when addressed_in_silence? then "Fine. Be that way!"
      else "Whatever."
      end
    end
    
    private 
    
    def shouted_at?
      !@message.empty? && @message == @message.upcase
    end
      
    def asked_a_question? 
      @message.end_with? ??
    end
    
    def addressed_in_silence?
      @message.empty?
    end
    
  end
  
end
