class Bob

  def hey message
    msg = Message.new message
    case 
    when msg.is_silence?
      "Fine. Be that way!"
    when msg.is_yelling?
      "Woah, chill out!"
    when msg.is_question?
      "Sure."      
    else
      "Whatever."
    end      
  end

  class Message

    def initialize message
      @message = message.strip
    end
    
    def is_silence?
      @message.empty?
    end

    def is_yelling?
      @message.upcase == @message && @message =~ /[a-z]/i
    end

    def is_question?
      @message.end_with? "?"
    end
  end

end
