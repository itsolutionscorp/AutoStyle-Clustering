class Message

  def initialize(message)
    @message = message

    def is_yelling?
      (@message.upcase == @message) && (@message.downcase != @message.upcase)
    end

    def is_question?
      @message[-1] == "?"
    end

    def silence?
      @message.strip == ""
    end
  end
  
end

class Bob

  def hey(string)
    phrase = Message.new(string)
    if phrase.is_yelling?
      "Woah, chill out!"
    elsif phrase.is_question?
      "Sure."
    elsif phrase.silence?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

end
