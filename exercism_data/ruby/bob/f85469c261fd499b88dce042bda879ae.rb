class Bob
  def hey(message)
    if message.nil? or message.strip.size < 1
      'Fine. Be that way!'
    elsif message.upcase == message
      'Woah, chill out!'
    elsif message[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
