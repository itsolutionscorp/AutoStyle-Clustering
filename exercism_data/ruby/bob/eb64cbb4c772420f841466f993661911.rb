class Bob

  def hey(msg)
    case
    when msg.strip == ''
      'Fine. Be that way!'
    when msg =~ /[A-Z]/ && msg.upcase == msg
      'Woah, chill out!'
    when msg[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end

end
