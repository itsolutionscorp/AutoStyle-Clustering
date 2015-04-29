class Bob
  def hey message
    if message == message.upcase && message =~ /.*[A-z].*/
      'Woah, chill out!'
    elsif message[-1] == '?'
      'Sure.'
    elsif message.strip == ""
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
