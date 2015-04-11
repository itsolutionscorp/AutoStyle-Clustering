class Message
    def initialize(message)
      @message = message
    end

    def silence?
      !@message || @message.strip.empty?
    end

    def shouting?
      @message.upcase.eql?(@message)
    end

    def question?
      @message.end_with?('?')
    end
end

class Bob
  def hey(message)
    msg = Message.new(message)
    case
      when msg.silence? then 'Fine. Be that way!'
      when msg.shouting? then 'Woah, chill out!'
      when msg.question? then 'Sure.'
      else 'Whatever.'
    end
  end
end
