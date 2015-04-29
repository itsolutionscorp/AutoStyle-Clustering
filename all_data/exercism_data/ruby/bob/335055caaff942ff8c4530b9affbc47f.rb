class Bob

  def hey(message)
    if silent? message
      'Fine. Be that way.'
    elsif yelled? message
      'Woah, chill out!'
    elsif questioning? message
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silent?(message)
    message.nil? || message.empty?
  end

  def yelled?(message)
    message.upcase == message
  end

  def questioning?(message)
    message.end_with? '?'
  end

end
