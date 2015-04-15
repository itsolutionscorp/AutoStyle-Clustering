class IncomingMessage
  attr_reader :message

  def initialize(message)
    @message = message.to_s
  end

  def silence?
    message =~ /\A\s*\z/
  end

  def yelling?
    message == message.upcase
  end

  def questioning?
    message =~ /\?\z/
  end
end

class Bob
  class HeyHandler
    def handle(message)
      if message.silence?
        silence_response
      elsif message.yelling?
        yelling_response
      elsif message.questioning?
        question_response
      else
        standard_response
      end
    end

    def silence_response
      'Fine. Be that way!'
    end

    def yelling_response
      'Woah, chill out!'
    end

    def question_response
      'Sure.'
    end

    def standard_response
      'Whatever.'
    end
  end

  def hey(message)
    handler = HeyHandler.new
    handler.handle(IncomingMessage.new(message))
  end
end
