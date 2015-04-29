class Bob
  def hey(message)
    if silence?(message)
      "Fine. Be that way."
    elsif yelling?(message)
      "Woah, chill out!"
    elsif asking?(message)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def yelling?(message)
    message.upcase == message
  end

  def asking?(message)
    message.end_with?("?")
  end

  def silence?(message)
    message.nil? || message.empty?
  end
end
