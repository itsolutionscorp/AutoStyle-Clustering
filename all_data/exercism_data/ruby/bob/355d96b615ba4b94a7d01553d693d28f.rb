class Bob

  def hey(msg)
    msg = msg.strip
    return "Fine. Be that way!" if msg == ""
    return "Woah, chill out!" if msg.upcase == msg
    return "Sure." if msg[-1] == "?"
    "Whatever."
  end
end
