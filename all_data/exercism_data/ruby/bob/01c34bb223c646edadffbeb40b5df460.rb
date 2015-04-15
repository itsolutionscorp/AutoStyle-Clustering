class Bob
  def hey(text)
    message = Message.new(text)

    if message.empty?
      'Fine. Be that way!'
    elsif message.forcefully?
      'Woah, chill out!'
    elsif message.question?
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

    def forcefully?
      @text == @text.upcase
    end

    def question?
      @text.end_with?('?')
    end
  end
end
