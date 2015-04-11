class Bob
  
  def hey(speech)
    
    message = Message.new(speech)
    
    if message.empty?
      "Fine. Be that way!" 
    elsif message.forceful?
      "Woah, chill out!"   
    elsif message.question?
      "Sure."
    else
      "Whatever."
    end
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
