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

  def asking?(message)
    message.end_with? '?'
  end

  def shouting?(message)
    message.upcase == message
  end

  def silent?(message)
    message.to_s.empty?
  end
end
