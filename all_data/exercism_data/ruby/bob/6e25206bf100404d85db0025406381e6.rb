class Bob
  def hey message
    message = message.to_s.strip
    if message == message.upcase && message != ''
      'Woah, chill out!'
    elsif message.chars.last == '?'
      'Sure.'
    elsif message == ''
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
