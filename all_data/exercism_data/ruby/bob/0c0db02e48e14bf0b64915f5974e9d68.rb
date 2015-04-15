class Bob

  def hey(message)
    case
      when silence?(message)
        "Fine. Be that way!"
      when shouting?(message)
        "Woah, chill out!"
      when question?(message)
        "Sure."
      else
        "Whatever."
    end
  end

  protected

  def silence?(message)
    message.nil? || message.empty?
  end

  def shouting?(message)
    message == message.upcase
  end

  def question?(message)
    message.end_with?("?")
  end

end
