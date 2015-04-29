class Bob

  def hey(message)
    return "Woah, chill out!" if contains_character?(message) && is_upcase?(message)
    return "Sure." if is_question?(message)
    return "Fine. Be that way!" if is_empty?(message)
    return "Whatever." 
  end

  private 

  def is_upcase?(message)
    return true if message.<=>(message.upcase) == 0
    false
  end

  def is_question?(message)
    message.slice(-1) == "?"
  end
  
  def is_empty?(message)
    message =~ /^*\s$/ || message.empty?
  end

  def contains_character?(message)
    return true if message.match /[a-zA-Z]/
    false
  end
end
