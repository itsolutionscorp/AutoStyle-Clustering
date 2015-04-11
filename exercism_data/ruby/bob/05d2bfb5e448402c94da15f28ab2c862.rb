class Bob

  RESPONSE_TO_SILENCE = "Fine. Be that way!"
  RESPONSE_TO_YELLING = "Woah, chill out!"
  RESPONSE_TO_QUERIES = "Sure."
  RESPONSE_TO_BANALITY = "Whatever."

  class Message
    def initialize(message)
      @message = message
    end

    def silence?
      @message.match /^\s*$/
    end

    def yelling?
      @message.match /^[^a-z]+$/
    end

    def question?
      @message.match /\?$/
    end
  end

  def hey(incomingMessage)
    message = Message.new(incomingMessage)

    if message.silence?
      RESPONSE_TO_SILENCE
    elsif message.yelling?
      RESPONSE_TO_YELLING
    elsif message.question?
      RESPONSE_TO_QUERIES
    else
      RESPONSE_TO_BANALITY
    end
  end
end
