class Bob

  def hey(message)
    return "Fine. Be that way!" if no_message?(message)
    return "Woah, chill out!" if is_shouted?(message)  
    return "Sure." if is_question?(message)
    'Whatever.'
  end

  private

  def no_message?(message)
    message = String(message)
    message.strip.empty?
  end

  def is_question?(message)
    message.end_with?("?")
  end

  def is_shouted?(message)
    message == message.upcase
  end
end
