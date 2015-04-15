class Bob

  def hey(message)
    return "Fine. Be that way!" if message.strip.empty?
    unless is_shouting?(message)
      if message.end_with?("?")
        return "Sure."
      else
        return "Whatever."
      end
    end
    "Woah, chill out!"
  end

  def is_shouting?(message)
    message.upcase.eql?(message) && message.match(/[A-Z]/)
  end
end
