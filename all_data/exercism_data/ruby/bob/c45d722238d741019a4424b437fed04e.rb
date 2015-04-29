class Bob

  def hey(message)
    message.strip!
    if message.length == 0
      'Fine. Be that way!'
    elsif message == message.upcase && message =~ /[a-zA-Z]/
      'Woah, chill out!'
    elsif message.end_with? '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
