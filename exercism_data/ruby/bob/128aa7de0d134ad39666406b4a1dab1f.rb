class Bob
  
  def hey(speech)
    
    message = Message.new(speech)
    
    if message.empty?
      response = "Fine. Be that way!" 
    elsif message.forceful?
      response = "Woah, chill out!"   
    elsif message.question?
      response = "Sure."
    else
      response = "Whatever."
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
