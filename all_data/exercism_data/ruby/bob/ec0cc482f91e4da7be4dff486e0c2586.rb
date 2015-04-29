class Bob

  def hey(message)
    if is_empty?(message)
      "Fine. Be that way!"
    elsif is_yelling?(message)
      "Woah, chill out!"
    elsif is_question?(message)
      "Sure."
    else
      "Whatever."
    end
  end

  def is_empty?(message)
    message.strip.size == 0
  end

  def is_question?(message)
    message[-1] == "?"
  end

  def is_yelling?(message)
    message.upcase == message
  end

end
