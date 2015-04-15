class Bob

  def hey(message)
    if is_yelling?(message)
      "Woah, chill out!"
    elsif is_question?(message)
      "Sure."
    elsif silence?(message)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

  def is_yelling?(message)
    (message.upcase == message) && (message.downcase != message.upcase)
  end

  def is_question?(message)
    message[-1] == "?"
  end

  def silence?(message)
    message.strip == ""
  end
    
end
