class Bob
  class Message
    attr_reader :message

    def initialize(message)
      @message = message.to_s
    end

    def silent?
      message.empty?
    end

    def shouting?
      message.upcase == message
    end

    def question?
      message.end_with? '?'
    end
  end

  def hey(message)
    msg = Message.new(message)

    return 'Fine. Be that way!' if msg.silent?
    return 'Woah, chill out!'   if msg.shouting?
    return 'Sure.'              if msg.question?

    'Whatever.'
  end
end
