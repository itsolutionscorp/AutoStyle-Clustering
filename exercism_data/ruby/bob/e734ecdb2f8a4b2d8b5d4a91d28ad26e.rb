class Bob
  def hey(supplied_message)
    message = Message.new(supplied_message)
    response = Response.new

    if message.empty?
      response.empty
    elsif message.question?
      response.question
    elsif message.yelling?
      response.yell
    else
      response.no_match
    end
  end
end

class Message
  def initialize(supplied_message)
    @message = supplied_message
  end

  def empty?
    @message.nil? || @message.strip.empty?
  end

  def question?
    @message.end_with?("?") && @message.upcase != @message
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
