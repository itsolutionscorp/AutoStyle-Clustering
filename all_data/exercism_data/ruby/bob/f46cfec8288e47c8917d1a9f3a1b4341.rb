class Message
  def initialize(text)
    @text = text
  end

  def question?
    @text.end_with?('?')
  end

  def empty?
    @text.strip.empty?
  end

  def shouting?
    @text == @text.upcase
  end
end

class Bob
  def hey(text)
    message = Message.new(text)

    if message.empty?
      'Fine. Be that way!'
    elsif message.shouting?
      'Woah, chill out!'
    elsif message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
