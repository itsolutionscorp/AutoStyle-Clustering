class Bob
  def hey(message)
    message ||= ""

    return "Sure."              if question?(message)
    return "Whatever."          if tell_something?(message)
    return "Woah, chill out!"   if shouting?(message)
    return "Fine. Be that way!" if silence?(message)
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

  def tell_something?(message)
    !silence?(message) && !question?(message) && !shouting?(message)
  end
end
