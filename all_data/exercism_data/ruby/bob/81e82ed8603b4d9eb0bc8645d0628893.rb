class Bob
  def hey(message)
    message ||= ""

    message = Message.new(message)

    case
    when message.shouting?
      "Woah, chill out!"
    when message.question?
      "Sure."
    when message.empty?
      "Fine. Be that way."
    else
      "Whatever."
    end
  end

  private
  class Message
    def initialize(message)
     @message = message
    end

    def shouting?
      message =~ /[A-Z]{2,}/
    end

    def question?
      message =~ /\?$/
    end

    def empty?
      message == ""
    end

    private
    def message
      @message
    end
  end
end
