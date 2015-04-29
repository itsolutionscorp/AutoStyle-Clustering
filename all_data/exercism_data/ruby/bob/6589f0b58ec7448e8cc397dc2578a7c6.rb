class Bob

  def hey(message)
    return "Fine. Be that way!" if blank? message
    return "Woah, chill out!" if yell? message
    return "Sure." if question? message
    return "Whatever."
  end

  private

  def blank?(message)
    message.to_s.strip.empty?
  end

  def yell?(message)
    message == message.upcase
  end

  def question?(message)
    message.end_with?("?")
  end

end
