class Bob

  def hey(text)
    message = Message.new text
    case message
    when ~:none?
      'Fine. Be that way!'
    when ~:yelling?
      'Woah, chill out!'
    when ~:asking?
      'Sure.'
    else
      'Whatever.'
    end
  end

  class Message

    def initialize(text)
      @text = text
    end

    def none?
      @text.strip.empty?
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
