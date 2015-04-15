class Bob
  def hey(message)
    if says_nothing?(message)
      'Fine. Be that way!'
    elsif yelling?(message)
      'Woah, chill out!'
    elsif questioning?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def hey_using_returns(message)
    return 'Fine. Be that way!' if says_nothing?(message)
    return 'Woah, chill out!'   if yelling?(message)
    return 'Sure.'              if questioning?(message)
    return 'Whatever.'
  end

  def says_nothing?(message)
    message.nil? || message.strip == ''
  end

  def yelling?(message)
    message == message.upcase
  end

  def questioning?(message)
    message[-1] == '?'
  end
end
