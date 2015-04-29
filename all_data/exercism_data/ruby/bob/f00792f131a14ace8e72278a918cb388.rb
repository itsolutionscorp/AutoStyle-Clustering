class Bob

  def hey(message)
    return "Fine. Be that way!" if message.to_s.strip.empty?
    return "Woah, chill out!" if message == message.upcase
    return "Sure." if message.end_with?("?")
    return "Whatever."
  end

end
