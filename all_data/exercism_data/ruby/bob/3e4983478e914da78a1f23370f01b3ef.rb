class Bob
  def hey(message)
    return 'Fine. Be that way!' if says_nothing?(message)
    return 'Woah, chill out!'   if yelling?(message)
    return 'Sure.'              if questioning?(message)
    return 'Whatever.'
  end

  def says_nothing?(message)
    message.to_s.strip == ''
  end

  def yelling?(message)
    message == message.upcase
  end

  def questioning?(message)
    message.end_with?('?')
  end

  # Provided sample alternative to returns:
  def hey_using_case(message)
    case
    when says_nothing?(message) then 'Fine. Be that way!'
    when yelling?(message)      then 'Woah, chill out!'
    when questioning?(message)  then 'Sure.'
    else                             'Whatever.'
    end
  end

  # Provided sample alternative to returns:
  def hey_using_ifs(message)
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
end
