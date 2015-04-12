class Bob
  def hey(message)
    message = Message.new(message)

    case
      when message.silence?
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

class Message
  def initialize(message)
    @message = message
  end

  def yelling?
    @message.upcase == @message
  end

  def question?
    @message[-1] == '?'
  end

  def silence?
    @message.strip == ''
  end
end