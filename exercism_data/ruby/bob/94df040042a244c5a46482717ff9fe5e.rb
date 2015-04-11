class Bob
  
  def hey(message)
    return "Fine. Be that way!" if messageIsEmpty?message
    return "Woah, chill out!" if messageIsAllUppercase?message
    return "Sure." if messageIsQuestion?message
    return "Whatever."
  end
  
  private # helper methods follow
  
  def messageIsEmpty?(message)
    return (message.nil? or message.strip.empty?)
  end
  
  def messageIsAllUppercase?(message)
    return message.eql?message.upcase
  end
  
  def messageIsQuestion?(message)
    return message.end_with?'?'
  end
end
