class Bob
  class Message
    def initialize(text = nil)
      @text = text
    end

    def empty?
      @text.nil? || @text.strip.empty?
    end

    def yelling?
      @text.upcase == @text
    end

    def question?
      @text.end_with? '?'
    end
  end

  def hey(text = nil)
    message = Message.new text
    return 'Fine. Be that way!' if message.empty?
    return 'Woah, chill out!'   if message.yelling?
    return 'Sure.'              if message.question?
   'Whatever.'
  end
end
