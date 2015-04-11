class Bob
  def hey(text)
    message = Message.new(text)

    return 'Fine. Be that way!' if message.empty?
    return 'Woah, chill out!'   if message.shouting?
    return 'Sure.'              if message.question?

    'Whatever.'
  end

  private
  class Message
    attr_reader :text

    def initialize(text)
      @text = text
    end

    def empty?
      text.to_s.empty?
    end

    def shouting?
      text.upcase == text
    end

    def question?
      text.slice(-1) == '?'
    end
  end
end
