class Bob
  def hey(message)
    message = Message.new(message)
    if message.dumb?
      'Fine. Be that way!'
    elsif message.angry?
      'Woah, chill out!'
    elsif message.inquisitive?
      'Sure.'
    else
      "Whatever."
    end
  end
end

class Message
  def initialize(message)
    @content = message.to_s
  end

  def angry?
    @content.upcase == @content && !dumb?
  end

  def inquisitive?
    @content.end_with? "?"
  end

  def dumb?
    @content.lstrip.empty?
  end
end
