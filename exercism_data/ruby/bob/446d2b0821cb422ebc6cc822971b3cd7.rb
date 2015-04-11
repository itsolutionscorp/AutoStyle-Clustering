class Bob
  def hey(message)
    return "Fine. Be that way." if message.to_s.empty?
    return "Sure."              if message.end_with?("?")
    return "Woah, chill out!"   if message.upcase == message

    "Whatever."
  end
end
