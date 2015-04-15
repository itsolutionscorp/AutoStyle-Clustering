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
    @message = message
  end

  def silence?
    @message.strip.empty?
  end

  def question?
    @message.chars.last == '?'
  end

  def shout?
    return false if @message !~ /[[:alpha:]]/
    @message == @message.upcase
  end
end
