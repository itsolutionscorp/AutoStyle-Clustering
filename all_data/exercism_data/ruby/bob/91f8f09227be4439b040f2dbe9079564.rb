class Bob
  def hey(message)
    if silence?(message)
      "Fine. Be that way!"
    elsif shout?(message)
      "Woah, chill out!"
    elsif question?(message)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def shout?(message)
    message == message.upcase
  end

  def question?(message)
    message.end_with?("?")
  end

  def silence?(message)
    message.strip.size == 0
  end
end
