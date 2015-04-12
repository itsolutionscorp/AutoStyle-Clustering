class Bob
  
  def hey(speech)
    
    message = Message.new(speech)
    
    if message.silent_address?
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
  
  def silent_address?
    @message.to_s.strip.empty?
  end
  
  def forceful?
    @message == @message.upcase
  end
  
  def question?
    @message.end_with?('?')
  end
end