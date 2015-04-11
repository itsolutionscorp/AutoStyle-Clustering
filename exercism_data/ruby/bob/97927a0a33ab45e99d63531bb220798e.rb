class Bob

  def hey(message)
    if says_nothing? message
      'Fine. Be that way.'
    elsif yelling? message
      'Woah, chill out!'
    elsif question? message
      'Sure.'
    else
      'Whatever.'
    end
  end

  def says_nothing?(message)
    message.nil? || message.empty?
  end

  def yelling?(message)
    message.upcase! == nil
  end

  def question?(message)
    message.end_with? '?'
  end

end
