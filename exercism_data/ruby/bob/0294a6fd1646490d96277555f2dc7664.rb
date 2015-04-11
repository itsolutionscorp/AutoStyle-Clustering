class Bob
  def hey message
    return "Fine. Be that way!" if is_mute? message
    return "Woah, chill out!" if is_shout? message
    return "Sure." if is_query? message
    return "Whatever." if is_any? message
  end
  
  private
  def is_any? message
    true
  end
  def is_mute? message
    !message || message.strip.empty?
  end
  def is_shout? message
    message.upcase == message
  end
  def is_query? message
    message[-1] == "?"
  end
end
