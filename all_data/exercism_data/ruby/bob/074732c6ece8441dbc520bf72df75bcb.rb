class Bob

  def hey(message)
    msg = Message.new(message)

    with = if msg.silence?
      :silence
    elsif msg.shouting?
      :shouting
    elsif msg.asking?
      :asking
    else
      :talking
    end

    answer(with)
  end

  private

  def answer(with)
    case with
    when :silence
      'Fine. Be that way!'
    when :shouting
      'Woah, chill out!'
    when :asking
      'Sure.'
    when :talking
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
