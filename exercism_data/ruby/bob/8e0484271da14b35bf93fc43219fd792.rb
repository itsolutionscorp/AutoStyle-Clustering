class Bob
  def hey msg
    message = Message.new(msg)
    if message.silent?
      'Fine. Be that way!'
    elsif message.yell?
      'Woah, chill out!'
    elsif message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Message
  def initialize message
    @message = message
  end

  def silent?
    @message.strip.empty?
  end

  def yell?
    lowcase = @message.match(/\p{Lower}/)
    upcase = @message.match(/\p{Upper}/)
    !lowcase and upcase
  end

  def question?
    @message.end_with? '?'
  end
end
