class Bob
  def hey(message)
    return "Fine. Be that way!" if silence?(message)
    return "Woah, chill out!"   if shouting?(message)
    return "Sure."              if question?(message)
    return "Whatever."
  end

  private
  def silence?(message)
    message.nil? || message.empty?
  end

  def shouting?(message)
    message.upcase == message
  end

  def question?(message)
    message[-1] == "?"
  end
end
