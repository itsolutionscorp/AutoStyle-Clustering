class Bob
  def hey(message)
    if silence?(message)
      "Fine. Be that way!"
    elsif shouting?(message)
      "Woah, chill out!"
    elsif questioning?(message)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def silence?(message)
    message.nil? || String(message).chomp.empty?
  end

  def questioning?(message)
    String(message).end_with?("?")
  end

  def shouting?(message)
    String(message).upcase == message
  end
end
