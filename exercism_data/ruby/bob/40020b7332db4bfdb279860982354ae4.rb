class Bob
  def hey(message)
    if message.to_s.strip.length == 0 #catches nil and empty strings
      'Fine. Be that way!'
    elsif message == message.upcase
      'Woah, chill out!'
    elsif message[-1, 1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
