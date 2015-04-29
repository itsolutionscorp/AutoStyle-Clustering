class Bob

  def hey(message_to_bob)
    if silence?(message_to_bob)
      "Fine. Be that way!"
    elsif shouting?(message_to_bob)
      "Woah, chill out!"
    elsif question?(message_to_bob)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def shouting?(message)
    message == message.upcase
  end

  def question?(message)
    message.end_with?('?')
  end

  def silence?(message)
    message.to_s.strip.empty?
  end

end
