class Bob

  def hey speech

    message = Message.new(speech)

    case
    when message.yelling?
      "Woah, chill out!"
    when message.asking?
      "Sure."
    when message.silent?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  class Message

    def initialize speech
      @speech = speech
    end

    def yelling?
      @speech =~ /[[:alpha:]]/ && @speech == @speech.upcase
    end

    def asking?
      @speech.end_with?("?")
    end

    def silent?
      @speech.strip.empty?
    end

  end

end
