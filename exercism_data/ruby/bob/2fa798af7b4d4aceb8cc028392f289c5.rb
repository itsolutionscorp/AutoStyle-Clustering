class Bob
  def hey(message)
    return "Woah, chill out!" if all_caps?(message)
    return "Sure." if question?(message)
    return "Fine. Be that way!" if silence?(message)
    return "Whatever."
  end

  def all_caps?(message)
    message == message.upcase && /[a-zA-Z]/.match(message)
  end

  def question?(message)
    message.end_with?("?")
  end

  def silence?(message)
    message.strip.empty?
  end
end
