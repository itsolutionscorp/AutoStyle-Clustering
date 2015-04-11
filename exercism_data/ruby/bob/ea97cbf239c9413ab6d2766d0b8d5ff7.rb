class Bob

  def hey(message)
    if silent?(message)
      'Fine. Be that way.'
    elsif shouting?(message)
      'Woah, chill out!'
    elsif asking?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silent?(message)
    message.nil? || message.empty?
  end

  def shouting?(message)
    message == message.upcase
  end

  def asking?(message)
    message.end_with?("?")
  end

end
