class Bob
  def hey(message)
    case
    when message == nil || message == ''
      'Fine. Be that way.'
    when message[-1,1] == '?'
      'Sure.'
    when message == message.upcase
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end
