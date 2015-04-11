class Bob

  SILENT   = "Fine. Be that way."
  QUESTION = "Sure."
  SHOUTING = "Woah, chill out!"
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
        SILENT
      when shouting?
        SHOUTING
      when questioning?
        QUESTION
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
