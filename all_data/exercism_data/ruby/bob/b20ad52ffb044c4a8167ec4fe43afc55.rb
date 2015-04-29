class Bob

  def hey(message)
    return "Fine. Be that way!" if blank?(message)
    if shouting?(message)
      return "Woah, chill out!"
    elsif questioning?(message)
      return "Sure."
    else
      return "Whatever."
    end
  end

  def blank?(message)
    message.strip.empty?
  end

  def questioning?(message)
    message.end_with?("?")
  end

  def shouting?(message)
    message.upcase.eql?(message) && message.match(/[A-Z]/)
  end
end
