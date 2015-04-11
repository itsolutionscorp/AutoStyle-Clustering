class Bob
  def hey(message)
    case
    when message =~ /[A-Z]/ && message.upcase == message
      'Whoa, chill out!'
    when message =~ /\?\Z/
      'Sure.'
    when message =~ /\A\s*\Z/
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
