class Bob
  def hey(message)
    return "Fine. Be that way!" if silence?(message)
    return "Woah, chill out!" if yelling?(message)
    return "Sure." if question?(message)
    return "Whatever."
  end

  private

  def silence?(message)
    message.strip.empty?
  end

  def yelling?(message)
    message == message.upcase
  end

  def question?(message)
    message[-1] == "?"
  end
end
