class Bob

  def silence?(message)
    message.strip === ''
  end

  def yell?(str)
    # first need to see if it contains some letters
    if str =~ /[A-Z]/ 
      return str == str.upcase
    else
      return false
    end
  end

  def question?(str)
    str[str.length-1] == '?'
  end


  def hey(message)
    if yell?(message)
      'Woah, chill out!'
    elsif question?(message)
      'Sure.'
    elsif silence?(message)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end


end
