class Bob

  def hey(message)
    return "Fine. Be that way!" if message.strip.empty?
    if shouting?(message)
      return "Woah, chill out!"
    elsif message.end_with?("?")
      return "Sure."
    else
      return "Whatever."
    end
  end

  def shouting?(message)
    message.upcase.eql?(message) && message.match(/[A-Z]/)
  end
end
