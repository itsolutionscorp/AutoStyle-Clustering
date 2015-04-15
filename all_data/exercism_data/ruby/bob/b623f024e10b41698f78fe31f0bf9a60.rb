class Bob
  def hey(message)
    'Whatever.'
    'Sure.' if question?(message)
    'Woah, chill out!' if yelling?(message)
    'Fine. Be that way!' if silence?(message)
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
