class Bob
  attr_reader :msg
  
  def initialize
    @answers = {
      'Silent' => "Fine. Be that way.",
      'Question' => "Sure.",
      'Shouting' => "Woah, chill out!",
      'Default' => "Whatever."
    }
  end
  
  def hey(msg)
    @msg = msg
    @answers[message_kind]
  end
  
  private
  
    def message_kind
      if silence?
        'Silent'
      elsif shouting?
        'Shouting'
      elsif questioning?
        'Question'
      else
        'Default'
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
