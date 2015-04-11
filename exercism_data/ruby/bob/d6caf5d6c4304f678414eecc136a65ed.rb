class Bob 
  def hey(message)
    if message.strip == ""
      'Fine. Be that way!'
    elsif message == message.upcase && message =~ /[a-zA-Z]/
      'Woah, chill out!'
    elsif message[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
