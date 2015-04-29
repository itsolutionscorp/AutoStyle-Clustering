class Bob

  def hey str
    message = Message.new str
    if message.silent?
      'Fine. Be that way!'
    elsif message.shouting?
      'Woah, chill out!'
    elsif message.asking_politely?
      'Sure.'
    else
      'Whatever.'
    end
  end

end


class Message

  def initialize str
    @message = str
  end

  def silent?
    @message.strip.empty?
  end

  def shouting?
    @message.upcase == @message
  end

  def asking_politely?
    !shouting? && @message.end_with?('?')
  end

end
