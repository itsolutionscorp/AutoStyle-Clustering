class Bob
  def hey(text)
    TeenagerAnswer.new(text).answer
  end

  private
  class TeenagerAnswer
    attr_reader :text

    def initialize(text)
      @text = text.to_s
    end

    def answer
      return 'Fine. Be that way!' if message.empty?
      return 'Woah, chill out!'   if message.shouting?
      return 'Sure.'              if message.question?

      'Whatever.'
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
