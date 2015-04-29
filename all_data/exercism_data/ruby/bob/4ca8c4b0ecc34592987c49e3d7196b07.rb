class Bob
  def hey(text)
    message = Message.new(text)

    if message.silence?
      'Fine. Be that way!'
    elsif message.shout?
      'Woah, chill out!'
    elsif message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message
  def initialize(text)
    @text = text
  end

  def silence?
    @text.strip.empty?
  end

  def shout?
    @text == @text.upcase
  end

  def question?
    @text.end_with?('?')
  end
end
