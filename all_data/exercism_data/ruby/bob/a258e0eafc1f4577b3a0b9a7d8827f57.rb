class Bob
  
  def hey(message)
    return "Fine. Be that way." if empty?(message)
    return "Woah, chill out!" if shouting?(message)
    return "Sure." if asking_a_question?(message)
    "Whatever."
  end
  
  def shouting?(message)
    message.upcase == message
  end
  
  def asking_a_question?(message)
    message[-1] == '?'
  end
  
  def empty?(message)
    message.to_s == ""
  end
end
