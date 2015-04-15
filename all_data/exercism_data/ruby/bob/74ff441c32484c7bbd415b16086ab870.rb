class Bob

  def hey incoming_message

    message = Message.new incoming_message

    case
    when message.shout?
      'Woah, chill out!'
    when message.question?
      'Sure.'
    when message.silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Message
  def initialize message
    @message = message.to_s
  end

  def silence?
    @message.strip.empty?
  end

  def question?
    @message.end_with? '?'
  end

  def shout?
    @message.match(/[A-Z]/) && (@message.upcase == @message)
  end
end
