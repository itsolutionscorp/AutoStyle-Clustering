class Bob
  def hey(message)
    msg = Message.new(message)
    case
    when msg.silence?
      'Fine. Be that way.'
    when msg.shouting?
      'Woah, chill out!'
    when msg.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message
  attr_reader :message

  def initialize(msg)
    @message = msg
  end

  def silence?
    message.nil? || message.empty?
  end

  def shouting?
    message.upcase == message
  end

  def question?
    message.end_with?('?')
  end
end
