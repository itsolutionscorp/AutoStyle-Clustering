class Bob
  def hey(message)
    if silence?(message)
      'Fine. Be that way!'
    elsif shouty?(message)
      'Woah, chill out!'
    elsif question?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silence?(message)
    message =~ /^\s*$/
  end

  def shouty?(message)
    message.upcase == message
  end

  def question?(message)
    message[-1] == '?'
  end
end
