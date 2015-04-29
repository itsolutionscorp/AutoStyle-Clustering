class Bob
  
  def hey addressing_statement
    Exchange.new(addressing_statement).response
  end
  
  class Exchange
    
    def initialize request
      @request = request.strip
    end
    
    def response
      case
      when shouted_at?
        "Woah, chill out!"
      when asked_a_question? 
        "Sure."
      when addressed_in_silence 
        "Fine. Be that way!"
      else
        "Whatever."
      end
    end
      
    def shouted_at?
      !@request.empty? && @request == @request.upcase
    end
      
    def asked_a_question? 
      @request[-1] == ??
    end
    
    def addressed_in_silence
      @request.empty?
    end
    
  end
  
end
