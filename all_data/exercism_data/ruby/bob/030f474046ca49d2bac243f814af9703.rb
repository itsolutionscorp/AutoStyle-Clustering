class Bob

  def hey(message)
    if shouting?(message)
      "Woah, chill out!"
    elsif questioning?(message)
      "Sure."
    elsif silence?(message)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

  def shouting?(message)
    !message.match(/[a-z]/) && message.match(/[A-Z]/)
  end

  def questioning?(message)
    message.end_with?('?')
  end

  def silence?(message)
    message.strip.empty?
  end

end
