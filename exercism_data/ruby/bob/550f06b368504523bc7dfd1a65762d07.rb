class Bob

  INDIFFERENT   = "Fine. Be that way."
  ANSWER = "Sure."
  SHOUT = "Woah, chill out!"
  DEFAULT  = "Whatever."

  attr_reader :msg
    
  def hey(msg)
    @msg = msg
    message_kind
  end
  
  private
  
    def message_kind
      case
      when silence?
        INDIFFERENT
      when shouting?
        SHOUT
      when questioning?
        ANSWER
      else
        DEFAULT
      end
    end
    
    def silence?
      msg.to_s.empty?
    end
    
    def shouting?
      msg == msg.upcase
    end
    
    def questioning?
      msg.end_with? '?'
    end  
end
