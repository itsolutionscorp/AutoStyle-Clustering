class Bob
  def hey(msg)
    message = ConversationalMessage.new msg
    if message.ignored?
      return "Fine. Be that way."
    elsif message.shout?
      return "Woah, chill out!" 
    elsif message.question?
      return "Sure."
    else
      "Whatever."
    end
  end
end

class ConversationalMessage
  def initialize message
    @message = message
  end
  def question?
    @message.end_with? '?'
  end
  def ignored?
    @message.nil? || @message.empty?
  end
  def shout?
    @message.upcase == @message
  end
end
