class Bob
  
  def hey(speech)
    
    message = Message.new(speech)
    return "Fine. Be that way!" if message.empty?
    return "Woah, chill out!" if message.forceful?
    return "Sure." if message.question?
    return "Whatever."
  end
end

class Message
  def initialize(message)
    @message = message
  end
  
  def empty?
    @message.nil? or @message.strip.empty?
  end
  
  def forceful?
    @message == @message.upcase
  end
  
  def question?
    @message.end_with?'?'
  end
end
