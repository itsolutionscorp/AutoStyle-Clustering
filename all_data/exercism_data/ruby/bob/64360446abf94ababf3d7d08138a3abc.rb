class Bob
  def hey(text)
    TeenagerAnswer.new(text).answer
  end

  class TeenagerAnswer
    attr_reader :text

    def initialize(text)
      @text = text.to_s
    end

    def answer
      if message.empty?
        'Fine. Be that way!'
      elsif message.shouting?
        'Woah, chill out!'
      elsif message.question?
        'Sure.'
      else
        'Whatever.'
      end
    end

    def message
      @message ||= Message.new(text)
    end
  end

  class Message
    attr_reader :text

    def initialize(text)
      @text = text.to_s
    end

    def empty?
      text.empty?
    end

    def shouting?
      text.upcase == text
    end

    def question?
      text.end_with?('?')
    end
  end
end
