class Bob

  def hey(message)
    if silence?(message)
      "Fine, be that way."
    elsif ask_a_question?(message)
      "Sure."
    elsif shout?(message)
      "Woah, chill out!"
    else
      "Whatever."
    end
  end

  def shout?(message)
    message == message.upcase
  end

  def ask_a_question?(message)
    message.end_with?("?")
  end

  def silence?(message)
    message.to_s.empty?
  end

end
