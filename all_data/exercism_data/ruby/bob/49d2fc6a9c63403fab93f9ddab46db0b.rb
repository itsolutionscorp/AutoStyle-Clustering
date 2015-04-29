class Bob

  def hey(msg)
    message = Message.new(msg)

    case
    when message.shouting?
      'Whoa, chill out!'
    when message.asking?
      'Sure.'
    when message.silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  class Message
    attr_reader :message

    def initialize(msg)
      @message = msg
    end

    def shouting?
      message.gsub(/[^[:alpha:]]/, '').match(/^[[:upper:]]+$/)
    end

    def asking?
      message.end_with?('?') && !shouting?
    end

    def silence?
      message.strip.empty?
    end
  end
end
