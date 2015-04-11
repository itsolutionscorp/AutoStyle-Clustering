class Bob
  def hey(message)
    if shouting?(message)
      "Woah, chill out!"
    elsif question?(message)
      "Sure."
    elsif empty_message?(message)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private
  def question?(message)
    message.match /\A.*\?\z/
  end

  def empty_message?(message)
    message.strip.empty?
  end

  def shouting?(message)
    message == message.upcase && message =~ /[A-Z]/
  end
end
