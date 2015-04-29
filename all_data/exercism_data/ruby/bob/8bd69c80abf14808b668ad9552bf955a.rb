class Bob
  attr_reader :msg
  
  def initialize
    @answers = {
      silent: "Fine. Be that way.",
      question: 'Sure.',
      shouting: 'Woah, chill out!',
      default: "Whatever."
    }
  end
  
  def hey(msg)
    @msg = msg.to_s
    @answers[message_kind]
  end
  
  def message_kind
    if silence?
      :silent
    elsif shouting?
      :shouting
    elsif questioning?
      :question
    else
      :default
    end
  end
  
  def silence?
    msg.empty?
  end
  
  def shouting?
    msg == msg.upcase
  end
  
  def questioning?
    msg.end_with? '?'
  end  
end
