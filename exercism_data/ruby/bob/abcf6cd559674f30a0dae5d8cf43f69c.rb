class Bob

  def hey(message)
    if shouting?(message)
      return "Woah, chill out!"
    elsif question?(message)
      return "Sure."
    elsif empty_message?(message)
      return "Fine. Be that way!"
    end

    "Whatever."
  end

private

  def shouting?(message)
    message.match(/[a-z]/).nil? && message.match(/[A-Z]/)
  end

  def question?(message)
    message[-1] == "?"
  end

  def empty_message?(message)
    message.strip == ""
  end

end
