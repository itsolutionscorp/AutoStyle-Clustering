class Bob
  def hey(message)
    message_match = Message.new(:message => message)
    response = Response.new

    if message_match.empty_message?
      response.empty
    elsif message_match.question_message?
      response.question
    elsif message_match.yelling_message?
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

  def empty_message?
    @message == "" || @message.nil? || @message.gsub(/\s+/, "") == ""
  end

  def question_message?
    @message[@message.length-1] == "?" && @message.upcase != @message
  end

  def yelling_message?
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
