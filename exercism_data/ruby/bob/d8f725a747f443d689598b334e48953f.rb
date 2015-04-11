class Bob
  def hey(text)

    message = Message.new(text)

    case
    when message.is_empty?
      'Fine. Be that way.'
    when message.is_yell?
      'Woah, chill out!'
    when message.is_question?
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
  def is_question?
    @message.end_with?('?')
  end
  def is_empty?
    @message.nil? || @message.empty?
  end
  def is_yell?
    @message == @message.upcase
  end
end
