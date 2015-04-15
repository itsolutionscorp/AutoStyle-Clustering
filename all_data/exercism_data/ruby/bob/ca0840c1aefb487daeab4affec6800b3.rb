class Bob
  def hey(message)
    message = Message.new(message)

    case
    when message.silence?
      'Fine. Be that way.'
    when message.shouting?
      'Woah, chill out!'
    when message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message
  def initialize(message)
    @message = message
  end

  def silence?
    @message.nil? || @message.strip.empty?
  end

  def question?
    @message.end_with?('?')
  end

  def shouting?
    !silence? && !@message.match(/\p{Lower}/)
  end
end
