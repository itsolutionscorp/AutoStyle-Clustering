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
