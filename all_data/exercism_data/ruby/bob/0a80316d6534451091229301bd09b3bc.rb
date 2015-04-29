class Bob
  def hey(message)
    msg = Message.new(message)
    case
    when msg.is_silence?
      'Fine. Be that way.'
    when msg.is_shouting?
      'Woah, chill out!'
    when msg.is_a_question?
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

  def is_silence?
    message == '' || message == nil
  end
  def is_shouting?
    message.upcase == message
  end
  def is_a_question?
    message.end_with?('?')
  end
end
