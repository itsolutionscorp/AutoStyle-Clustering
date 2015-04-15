class Bob
  REPLIES = {
    silent?: 'Fine. Be that way!',
    yell?: 'Woah, chill out!',
    question?: 'Sure.'
  }

  def hey msg
    message = Message.new(msg)
    REPLIES.each do |kind, reply|
      return reply if message.send(kind)
    end
    'Whatever.'
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
    lowcase = @message.match(/[[:lower:]]/)
    upcase = @message.match(/[[:upper:]]/)
    !lowcase and upcase
  end

  def question?
    @message.end_with? '?'
  end
end
