class Bob
  def hey(message)
    return response(message)
  end

  def response(message)
    case
    when message.rstrip == ''
      return 'Fine. Be that way!'
    when message === message.upcase
      return 'Woah, chill out!'
    when "?" == message[message.length-1, message.length-1]
      return 'Sure.'      
    else
      return 'Whatever.'
    end
  end
end
