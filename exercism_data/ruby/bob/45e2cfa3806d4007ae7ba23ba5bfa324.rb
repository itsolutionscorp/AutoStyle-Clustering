class Bob
  def hey(message)
    message_match = Message.new(:message => message)
    response = Response.new

    if message_match.empty?
      response.empty
    elsif message_match.question?
      response.question
    elsif message_match.yelling?
      response.yell
    else
      response.no_match
    end
  end
end

class Message
  def initialize(args)
    @message = args[:message]
  end

  def empty?
    @message.nil? || @message.strip.empty?
  end

  def question?
    @message.chars.last == "?" && @message.upcase != @message
  end

  def yelling?
    @message == @message.upcase
  end
end

class Response
  def empty
    "Fine. Be that way!"
  end

  def question
    "Sure."
  end

  def yell
    "Woah, chill out!"
  end

  def no_match
    "Whatever."
  end
end
