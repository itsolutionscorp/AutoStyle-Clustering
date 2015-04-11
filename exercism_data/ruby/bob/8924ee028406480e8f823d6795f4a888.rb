class Bob
  def hey(message)
    case
    when message =~ /\A\s*\z/
      'Fine. Be that way!'
    when message == message.upcase && message =~ /[[:alpha:]]/
      'Woah, chill out!'
    when message =~ /\?\z/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
