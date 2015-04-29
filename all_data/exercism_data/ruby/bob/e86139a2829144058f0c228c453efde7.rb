class Bob
  def hey(message)
    return 'Fine. Be that way!' if blank?(message)
    
    return 'Woah, chill out!' if shouting?(message)

    return 'Sure.' if question?(message)
    
    return 'Whatever.'
  end
  
  def shouting?(message)
    message == message.upcase 
  end
  
  def blank?(message)
    message.strip.length == 0
  end
  
  def question?(message)
    !!message.match(/\?$/)
  end
  
end
