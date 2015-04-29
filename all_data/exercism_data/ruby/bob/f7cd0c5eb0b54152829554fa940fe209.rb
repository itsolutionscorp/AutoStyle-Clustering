class Message
  def initialize(message)
    @message = message
  end

  def question?
    @message.end_with? '?'
  end

  def yelling?
    @message == @message.upcase
  end

  def nothing?
    @message.nil? || @message.strip.empty?
  end
end

class Bob
  def hey(message_str)
    message = Message.new(message_str)

    case
    when message.nothing?
      'Fine. Be that way!'
    when message.yelling?
      'Woah, chill out!'
    when message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end
