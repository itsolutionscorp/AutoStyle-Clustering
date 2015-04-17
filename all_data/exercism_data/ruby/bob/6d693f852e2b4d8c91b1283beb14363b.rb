class Bob

  def hey(message)
    return "Fine. Be that way!" if no_message?(message)
    return "Woah, chill out!" if is_shouted?(message)  
    return "Sure." if is_question?(message)
    'Whatever.'
  end

  private

  def no_message?(message)
    message.nil? || message.strip.empty?
  end

  def is_question?(message)
    /\?\Z/ =~ message
  end

  def is_shouted?(message)
    /^[^a-z]*$/ =~ message
  end
end