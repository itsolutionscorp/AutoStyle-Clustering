class Bob
  def hey(input)
    message = Message.new(input)
    case
    when message.silent?
      'Fine. Be that way.'
    when message.questioning?
      'Sure.'
    when message.shouting?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private
  class Message
    def initialize(message)
      @message = message
    end

    def silent?
      @message.nil? || @message.empty?
    end

    def questioning?
      @message.end_with?('?')
    end

    def shouting?
      @message.eql?(@message.upcase)
    end
  end
end
