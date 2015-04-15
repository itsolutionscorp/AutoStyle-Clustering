class Bob

  def hey(message)
    if  no_response?(message)
      'Fine. Be that way!'
    elsif yelling?(message)
      'Woah, chill out!'
    elsif question?(message)
      "Sure."
    else
      'Whatever.'
    end
  end

  def no_response?(message)
    message.scan(/\w+/).empty?
  end

  def yelling?(message)
    message == message.upcase && !no_response?(message)
  end

  def question?(message)
    message.end_with?("?")
  end

end
