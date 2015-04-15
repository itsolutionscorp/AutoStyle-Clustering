class Bob
  def hey(text)
    message = Message.new(text)

    if message.question?
      'Sure.'
    elsif message.shouting?
      'Woah, chill out!'
    elsif message.silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Message
  def initialize(text)
    @text = text.to_s
  end

  def silence?
    @text.empty?
  end

  def question?
    !shouting? && @text.end_with?('?')
  end

  def shouting?
    all_caps = (@text.upcase == @text)
    !@text.empty? && all_caps
  end
end
