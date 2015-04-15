class Bob
  def hey(message)
    message = Message.new(message)
    message.answer
  end

  class Message
    def initialize(content)
      @content = content
    end

    def empty?
      @content.strip.empty?
    end

    def yelling?
      @content == @content.upcase
    end

    def question?
      @content.end_with? '?'
    end

    def answer
      return "Fine. Be that way!" if empty?
      return "Woah, chill out!" if yelling?
      return "Sure." if question?
      "Whatever."
    end
  end
end
