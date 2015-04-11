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
      @text = text.to_s
    end

    def empty?
      @text.strip.empty?
    end

    def forcefully?
      @text == @text.upcase
    end

    def question?
      @text.end_with?('?')
    end
  end
end
