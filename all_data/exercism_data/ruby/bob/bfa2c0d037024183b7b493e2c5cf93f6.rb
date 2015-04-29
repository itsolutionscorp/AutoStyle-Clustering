class Bob

  def hey(message)
    if silent?(message)
      'Fine. Be that way!'
    elsif shouting?(message)
      'Woah, chill out!'
    elsif question?(message)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def question?(message)
     message.end_with?('?')
  end

  def shouting?(message)
    message.to_s[/[:upper:]/] && !message.to_s[/[:lower:]/]
  end

  def silent?(message)
    message.nil? || message == ''
  end

end
