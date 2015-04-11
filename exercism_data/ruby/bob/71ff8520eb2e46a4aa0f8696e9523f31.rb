class Bob

  def hey (msg)
    respond msg
  end
  
  private
  def respond msg
    message = ConversationalMessage.new msg
    if message.ignoring?
      "Fine. Be that way."
    elsif message.shouting?
      "Woah, chill out!" 
    elsif message.asking?
      "Sure."
    else
      "Whatever."
    end
  end

end

class ConversationalMessage
  attr_reader :message

  def initialize message
    @message = message
  end

  def asking?
    message.end_with? '?'
  end

  def ignoring?
    message.nil? || message.empty?
  end

  def shouting?
    message.upcase == message
  end
end
