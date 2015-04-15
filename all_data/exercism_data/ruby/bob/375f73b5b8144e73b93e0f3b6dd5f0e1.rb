class Bob

  def hey(text = nil)
    message = Message.new text
    case message
    when :blank?.to_proc
      'Fine. Be that way!'
    when :yelling?.to_proc
      'Woah, chill out!'
    when :asking?.to_proc
      'Sure.'
    else
      'Whatever.'
    end
  end

  class Message

    def initialize(text)
      @text = text
    end

    def blank?
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
