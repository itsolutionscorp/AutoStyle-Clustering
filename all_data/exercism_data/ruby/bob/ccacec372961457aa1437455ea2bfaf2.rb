class Bob
  def hey(message)
    message ||= ""

    return "Sure."              if question?(message)
    return "Woah, chill out!"   if shouting?(message)
    return "Fine. Be that way!" if silence?(message)
    return "Whatever."
  end

  private
  def silence?(message)
    message.empty?
  end

  def shouting?(message)
    !silence?(message) && (message.upcase == message)
  end

  def question?(message)
    !shouting?(message) && (message[-1] == "?")
  end
end
