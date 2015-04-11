class Bob

  RESPONSE_TO_SILENCE = "Fine. Be that way!"
  RESPONSE_TO_YELLING = "Woah, chill out!"
  RESPONSE_TO_QUERIES = "Sure."
  RESPONSE_TO_BANALITY = "Whatever."

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

  class Message
    def initialize(message)
      @message = message
    end

    def silence?
      @message.strip.empty?
    end

    def yelling?
      @message.eql? @message.upcase
    end

    def question?
      @message.end_with? '?'
    end
  end
end
