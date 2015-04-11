class Bob
  def hey(message)
    return "Fine. Be that way." if message.nil?
    return "Fine. Be that way." if message == ""

    if message.upcase == message
      "Woah, chill out!"
    elsif message.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end
end
