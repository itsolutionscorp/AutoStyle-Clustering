class Bob

  def hey message
    if isSilent?(message)
      'Fine. Be that way!'
    elsif isShouting?(message)
      'Woah, chill out!'
    elsif isAskingPolitely?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def isSilent? message
    message.strip.empty?
  end

  def isShouting? message
    message.upcase == message
  end

  def isAskingPolitely? message
    !isShouting?(message) && message[-1] == '?'
  end

end
