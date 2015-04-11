class Bob
  def hey(text)

    message = Message.new(text)

    case
    when message.empty?
      'Fine. Be that way.'
    when message.yell?
      'Woah, chill out!'
    when message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message
  attr_reader :message

  def initialize(message)
    @message = message
  end
  def question?
    message.end_with?('?')
  end
  def empty?
    message.nil? || @message.empty?
  end
  def yell?
    message == message.upcase
  end
end
