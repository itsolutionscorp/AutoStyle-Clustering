class Bob
  
  attr_reader :message
  
  def hey(message)
    
    @message = message  
    
    return 'Fine. Be that way!' if is_nothing?     
    return 'Sure.' if is_question?
    return 'Woah, chill out!' if is_yelling?
    return 'Whatever.' 
    
  end
  
  def is_question?
    true if message.end_with?('?') and message != message.upcase
  end

  def is_yelling?
    true if  message == message.upcase 
  end
  
  def is_nothing?
    true if  message.strip.empty?
  end
    
end
