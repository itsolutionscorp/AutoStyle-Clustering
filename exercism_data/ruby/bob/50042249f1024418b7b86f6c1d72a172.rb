class Bob

  def hey(message)
    if shouting? message
      "Woah, chill out!"
    elsif question? message
      "Sure."
    elsif silence? message
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

  def shouting?(message)
    message == message.upcase && message.match(/[a-z]/i)
  end

  def question?(message)
    message.match(/\?\z/)
  end

  def silence?(message)
    message.match(/\A *\z/)
  end

end
