class Bob
  def hey(message)
    if silence?(message)
      "Fine. Be that way!"
    elsif shouting?(message)
      "Woah, chill out!"
    elsif asking_a_question?(message)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def shouting?(message)
    message == message.upcase
  end

  def asking_a_question?(message)
    message.end_with?("?")
  end

  def silence?(message)
    message.strip == ""
  end
end
