class Bob

  def hey(message)
    case
    when silence?(message)
      "Fine. Be that way!"
    when shouting?(message)
      "Woah, chill out!"
    when question?(message)
      "Sure."
    else
      "Whatever."
    end
  end

  private 

  def letters?(message)
    message =~ /[A-Z|a-z]/
  end

  def shouting?(message)
    if letters?(message)
      message.upcase == message
    end
  end

  def question?(message)
    message.end_with?("?")
  end

  def silence?(message)
    message.strip.empty?
  end
end
