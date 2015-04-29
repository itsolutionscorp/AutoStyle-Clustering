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
    alphas = message.gsub(/[^a-z]/i, '')
    alphas.upcase == alphas
  end

  def isAskingPolitely? message
    not isShouting?(message) and message[-1] == '?'
  end

end
