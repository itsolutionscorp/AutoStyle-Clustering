class Bob
  def hey message
    responder = ConversationResponder.new(message)
    responder.answer
  end

  class ConversationResponder
    def initialize message
      self.message = message || ''
    end

    def answer
      case 
      when you_dont_say_anything
        'Fine. Be that way!'
      when you_yell_at_him
        'Woah, chill out!'
      when you_ask_him_a_question
        'Sure.'
      else
        'Whatever.'
      end
    end

  private
    attr_accessor :message
    def you_dont_say_anything
      message.strip.empty?
    end

    def you_yell_at_him
      message == message.upcase
    end

    def you_ask_him_a_question
      message.match(/\?$/)
    end
  end
end
