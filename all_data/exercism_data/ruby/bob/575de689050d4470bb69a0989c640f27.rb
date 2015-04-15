class Bob
  def hey(message)
    message.strip!
    if message =~ /[A-Z]/ && message !~ /[a-z]/
      'Woah, chill out!'
    elsif message[-1] == '?'
      'Sure.'
    elsif message == ''
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
