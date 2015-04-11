class Bob
  def hey(message)
    if silent?(message)
      'Fine. Be that way.'
    elsif shouting?(message)
      'Woah, chill out!'
    elsif question?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def question?(message)
    message[-1] == '?'
  end

  def shouting?(message)
    message == message.upcase
  end

  def silent?(message)
    message.nil? or message.empty?
  end
end
