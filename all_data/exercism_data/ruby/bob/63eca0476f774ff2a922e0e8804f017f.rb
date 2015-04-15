class Bob
  def hey (msg)
    message = ConversationalMessage.new msg
    response = ResponseMessage.new message
    response.respond
  end
end

class ConversationalMessage
  def initialize message
    @message = message
  end
  def asking?
    @message.end_with? '?'
  end
  def ignoring?
    @message.nil? || @message.empty?
  end
  def shouting?
    @message.upcase == @message
  end
end

class ResponseMessage
  def initialize message
    @message = message
  end
  def respond
    if @message.ignoring?
      return "Fine. Be that way."
    elsif @message.shouting?
      return "Woah, chill out!" 
    elsif @message.asking?
      return "Sure."
    else
      "Whatever."
    end
  end
end
