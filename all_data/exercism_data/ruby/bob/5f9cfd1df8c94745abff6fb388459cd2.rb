class Bob
  def hey(message)
    return "Woah, chill out!" if message.upcase == message and message.upcase.match(/[A-Z]/)
    return "Sure." if message[message.length-1] == "?"
    return "Fine. Be that way!" if message.strip == ""
    "Whatever."
  end
end
