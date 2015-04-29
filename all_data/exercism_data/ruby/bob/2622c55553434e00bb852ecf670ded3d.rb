class Bob
  def hey(message)
    if silent?(message) || message.nil?
      'Fine. Be that way!'
    elsif shouting?(message)
      'Woah, chill out!'
    elsif question?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def silent?(message)
    message == ''
  end

  def shouting?(message)
    message.upcase == message 
  end

  def question?(message)
    message[message.length - 1] == '?'
  end
end
