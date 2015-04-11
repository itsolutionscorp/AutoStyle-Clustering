class Bob
  def hey(message)
    return "Woah, chill out!" if shout?(message)
    return "Sure." if question?(message)
    return "Fine. Be that way!" if silence?(message)
    return "Whatever."
  end

  def shout?(message)
    message == message.upcase && /[A-Z]/.match(message)
  end

  def question?(message)
    message.end_with?("?")
  end

  def silence?(message)
    message.strip.empty?
  end
end
