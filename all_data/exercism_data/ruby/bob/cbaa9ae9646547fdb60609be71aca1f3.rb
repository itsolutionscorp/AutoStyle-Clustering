class Bob
  def hey(message)
    return 'Woah, chill out!' if shouting?(message)

    return 'Sure.' if message.match(/\?$/)
    
    return 'Fine. Be that way!' if blank?(message)
    
    return 'Whatever.'
  end
  
  def shouting?(message)
    message == message.upcase && !blank?(message)
  end
  
  def blank?(message)
    message.split("").select{|c| !c.match /\s/ }.length == 0
  end
  
end
