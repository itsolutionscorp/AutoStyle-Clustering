class Bob

  def hey(text = nil)
    message = Message.new text
    case message
    when &:empty?
      'Fine. Be that way!'
    when &:yelling?
      'Woah, chill out!'
    when &:asking?
      'Sure.'
    else
      'Whatever.'
    end
  end

  class Message

    def initialize(text)
      @text = text
    end

    def empty?
      @text.nil? || @text.strip.empty?
    end

    def yelling?
      @text.eql?(@text.upcase)
    end

    def asking?
      @text.end_with?("?")
    end
  end
end

class Symbol
  alias ~ to_proc
end
