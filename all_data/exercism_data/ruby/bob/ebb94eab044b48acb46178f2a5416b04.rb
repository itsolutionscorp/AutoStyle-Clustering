class Bob

  def hey(message)
    msg = Message.new(message)

    if msg.silence?
      'Fine. Be that way!'
    elsif msg.shouting?
      'Woah, chill out!'
    elsif msg.asking?
      'Sure.'
    else
      'Whatever.'
    end
  end

end

class Message
  def initialize(message)
    @message = message.to_s
  end

  def silence?
    @message.strip.empty?
  end

  def shouting?
    @message == @message.upcase
  end

  def asking?
    @message.end_with?('?')
  end
end
