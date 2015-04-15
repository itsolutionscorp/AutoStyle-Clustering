class Bob
  def hey(message)
    silence(message) || shouting(message) || question(message) || "Whatever."
  end

private

  def silence(message)
    return nil unless message.strip.empty?
    "Fine. Be that way!"
  end

  def shouting(message)
    return nil unless message.upcase == message
    "Woah, chill out!"
  end

  def question(message)
    return nil unless message.end_with?("?")
    "Sure."
  end
end
