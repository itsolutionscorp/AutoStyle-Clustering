class Bob  
  attr_reader :message
  
  def hey(message)
    @message = message  
    
    return 'Fine. Be that way!' if nothing? 
    return 'Woah, chill out!' if yelling?    
    return 'Sure.' if question?
    'Whatever.' 
    
  end
  
  private
  
  def question?
     message.end_with?('?')
  end

  def yelling?
     message == message.upcase 
  end
  
  def nothing?
     message.strip.empty?
  end
    
end
