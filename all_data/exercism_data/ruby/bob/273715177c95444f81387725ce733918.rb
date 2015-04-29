class Bob
  def hey(message)
    if silence?(message)
      "Fine. Be that way!"
    elsif all_caps?(message)
      "Woah, chill out!"
    elsif question?(message)
      "Sure."
    else
      "Whatever."
    end
  end

  private
  def silence?(message)
    message.strip.empty?
  end

  def all_caps?(message)
    message == message.upcase
  end

  def question?(message)
    message.end_with?("?")
  end
end
