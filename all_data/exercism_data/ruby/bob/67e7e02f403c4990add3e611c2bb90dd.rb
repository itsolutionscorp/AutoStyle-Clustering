class Bob
  def hey message
    message = message ? message.strip : message
    if message == ''
      'Fine. Be that way!'
    elsif message !~ /[a-z]/
      'Woah, chill out!'
    elsif message[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
