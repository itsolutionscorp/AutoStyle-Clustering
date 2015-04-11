class Bob
  def hey(message)
    if silence?(message)
      "Fine. Be that way."
    elsif question?(message)
      "Sure."
    elsif yelling?(message)
      "Woah, chill out!"
    else
      "Whatever."
    end
  end

  private

  def yelling?(message)
    message == message.upcase
  end

  def question?(message)
    message.end_with?("?")
  end

  def silence?(message)
    message == "" || message.nil?
  end
end
