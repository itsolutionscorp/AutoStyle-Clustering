class Bob

  def hey(message)
    teenage_response(message)
  end

  def teenage_response(message)
    if nothing?(message) then 'Fine. Be that way!'
    elsif yelling?(message) then 'Woah, chill out!'
    elsif question?(message) then 'Sure.'
    else 'Whatever.'
    end
  end

  def nothing?(message)
    message.strip == ''
  end

  def yelling?(message)
    /[A-Z]/ =~ message && message.upcase == message
  end

  def question?(message)
    message[-1] == '?'
  end

end
