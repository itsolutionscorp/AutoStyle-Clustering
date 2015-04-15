class Bob

  RESPONSES = {
    question: 'Sure.',
    silence: 'Fine. Be that way!',
    shouting: 'Woah, chill out!',
    default: 'Whatever.'    
  }

  def hey(message_text)

    message = Message.new(message_text)

    case
    when message.empty?
      RESPONSES[:silence]
    when message.all_caps?
      RESPONSES[:shouting]
    when message.question?
      RESPONSES[:question]
    else
      RESPONSES[:default]
    end    
  end

  class Message
    def initialize(message_text)
      @message_text = message_text.strip
    end

    def empty?
      @message_text.empty?
    end

    def all_caps?
      @message_text == @message_text.upcase
    end

    def question?
      @message_text.end_with? '?'
    end
  end

end
