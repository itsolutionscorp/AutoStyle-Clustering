class Bob
  def hey(message)
    if silence?(message)
      "Fine. Be that way!"
    elsif aggressive?(message)
      "Woah, chill out!"
    elsif question?(message)
      "Sure."
    else
      "Whatever."
    end
  end

  def question?(message)
    message.end_with?("?")
  end

  def aggressive?(message)
    message == message.upcase
  end

  def silence?(message)
    message.nil? || message.empty?
  end
end
