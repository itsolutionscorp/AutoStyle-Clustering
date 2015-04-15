class Bob
  def hey(message)
    return "Fine. Be that way." if message.to_s.empty?
    return "Woah, chill out!" if message == message.upcase
    return "Sure." if message.match(/\?\Z/)
    "Whatever."
  end
end
