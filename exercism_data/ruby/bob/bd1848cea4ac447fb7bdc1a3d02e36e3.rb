class Bob
  
  def hey message
    Response.new(message).send
  end
  
  class Response
    
    def initialize message
      @message = message.strip
    end
    
    def send
      case
      when !shouted_at? && asked_a_question? then "Sure."
      when shouted_at? then "Woah, chill out!"
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
