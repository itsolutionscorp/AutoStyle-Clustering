class Bob
  def hey(message)
    message = message.to_s

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
    message.strip == ''
  end

  def shouty?(message)
    message =~ /[A-Z]/ && message.upcase == message
  end

  def question?(message)
    message.end_with?('?')
  end
end
