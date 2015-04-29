class Bob

  def hey(message)
    message_content = Interpret.new(message)

    case
      when message_content.silence?
        "Fine. Be that way!"
      when message_content.shouting?
        "Woah, chill out!"
      when message_content.question?
        "Sure."
      else
        "Whatever."
    end
  end

  class Interpret
    attr_reader :message

    def initialize(message)
      @message = message
    end

    def question?
      message.end_with?('?')
    end

    def shouting?
      has_letters? && (message.upcase == message)
    end

    def silence?
      message.strip.empty?
    end

    private

    def has_letters?
      message =~ /[a-zA-Z]/
    end
  end

end
