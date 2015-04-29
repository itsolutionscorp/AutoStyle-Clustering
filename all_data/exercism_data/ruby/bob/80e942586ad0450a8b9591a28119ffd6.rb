class Bob
  def hey(message)
    return "Fine. Be that way!" if silent?(message)
    return "Woah, chill out!"   if yelled?(message)
    return "Sure."              if question?(message)
    "Whatever."
  end

  private

  def question?(msg)
    msg.strip.end_with?('?')
  end

  def yelled?(msg)
    msg.upcase == msg
  end

  def silent?(msg)
    msg.strip == ""
  end
end
