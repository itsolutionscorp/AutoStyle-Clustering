class Bob
  def hey(message)
    response = 'Whatever.'
    response = 'Sure.' if question?(message)
    response = 'Woah, chill out!' if yelling?(message)
    response = 'Fine. Be that way!' if silence?(message)
    response
  end
  
  private
  
  def yelling?(message)
    message.upcase === message
  end
  
  def question?(message)
    message.end_with? '?'
  end
  
  def silence?(message)
    message.strip.empty?
  end
end
