class Bob

  def hey(message_text)
    Responder.new(message_text).respond
  end

  class Responder

    RESPONSES = {
        question: 'Sure.',
        silence: 'Fine. Be that way!',
        shouting: 'Woah, chill out!',
        default: 'Whatever.'
    }

    def initialize(message_text)
      @message = message_text.strip
    end

    def respond
      case
        when empty?
          RESPONSES[:silence]
        when all_caps?
          RESPONSES[:shouting]
        when question?
          RESPONSES[:question]
        else
          RESPONSES[:default]
      end
    end

    private

    def empty?
      @message.empty?
    end

    def all_caps?
      @message == @message.upcase
    end

    def question?
      @message.end_with? '?'
    end
  end

end
