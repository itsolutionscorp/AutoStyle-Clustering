class Bob

  def hey(message)
    # Silence:
    if message.strip.empty?
      "Fine. Be that way!"
    # Shouting:
    elsif message.upcase == message
      "Woah, chill out!"
    # Question:
    elsif message.end_with?('?')
      "Sure."
    # Other:
    else
      "Whatever."
    end
  end

end
