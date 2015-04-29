class Bob

  def hey(word)
    MessageHandler.new(word).handle
  end

  class MessageHandler
    def initialize(message)
      @message = message
    end

    def silence?
      @message.to_s.empty?
    end

    def shouting?
      @message.upcase == @message
    end

    def question?
      @message.end_with? '?'
    end

    def handle
      case
      when silence?
        "Fine. Be that way!"
      when shouting?
        "Woah, chill out!"
      when question?
        "Sure."
      else
        "Whatever."
      end
    end
  end
end
