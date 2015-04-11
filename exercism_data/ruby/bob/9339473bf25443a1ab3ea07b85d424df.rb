class Bob
  def hey(message)
    if silence?(message)
      "Fine. Be that way!"
    elsif aggressive?(message)
      "Woah, chill out!"
    elsif question?(message)
      "Sure."
    else
      "Whatever."
    end
  end

  def question?(message)
    message[-1] == '?'
  end

  def aggressive?(message)
    message == message.upcase
  end

  def silence?(message)
    message.to_s == ''
  end
end
