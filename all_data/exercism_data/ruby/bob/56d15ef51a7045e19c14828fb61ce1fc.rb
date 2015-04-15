class Bob
  def hey message
    if message.strip.length == 0
      'Fine. Be that way!'
    elsif message == message.upcase
      'Woah, chill out!'
    elsif message.end_with? '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
