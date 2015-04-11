class Bob

  class Message
    def initialize(msg)
      @msg = String(msg)
    end
    
    def empty?
      @msg.empty?
    end
    
    def shouting?
      @msg == @msg.upcase
    end

    def asking_a_question?
      @msg.end_with? '?'
    end
  end

  def hey(message)
    m = Message.new(message)
    case
    when m.empty? then "Fine. Be that way."
    when m.shouting? then "Woah, chill out!"
    when m.asking_a_question? then "Sure."
    else "Whatever."
    end
  end

end
