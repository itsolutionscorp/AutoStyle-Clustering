class Bob
  
  def hey message
    case object
    when yelling? message
      'Woah, chill out!'
    when question? message
      'Sure.'
    when silence? message
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def yelling? message
    message == message.upcase && message =~ /.*[A-z].*/
  end

  def question? message
    message[-1] == '?'
  end

  def silence? message
    message.strip == ""
  end

end