class Bob

  def hey(msg)
    return "Fine. Be that way." if msg.empty?
    return "Woah, chill out!" if shouting?(msg)
    return "Sure." if question?(msg)
    "Whatever."
  end

  def shouting?(msg)
    msg.upcase == msg
  end

  def question?(msg)
    msg.end_with?("?")
  end
end
