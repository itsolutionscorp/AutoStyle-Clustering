class Bob
  def hey(message, message2 = 0)
    if silent?(message)
      'Fine. Be that way!'
    elsif shouting?(message)
      'Woah, chill out!'
    elsif question?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def question?(message)
    message.end_with?("?")
  end

  def shouting?(message)
    message == message.upcase && message =~ /[A-z]/
  end

  def silent?(message)
     message.strip.empty?
  end
end
