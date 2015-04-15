class Bob
  def hey(message)
    if silence?(message)
      'Fine. Be that way!'
    elsif shouting?(message)
      'Woah, chill out!'
    elsif question?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silence?(message)
    message.strip.length == 0
  end

  def shouting?(message)
    message == message.upcase
  end

  def question?(message)
    message.end_with?('?')
  end
end
