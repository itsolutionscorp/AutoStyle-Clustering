class Bob
  def hey(message)
    if message.match(/^\s*\Z/)
      'Fine. Be that way!'
    elsif message.match(/[A-Z]/) and message.upcase == message
      'Woah, chill out!'
    elsif message.match(/\?\Z/)
      'Sure.'
    else
      'Whatever.'
    end
  end
end
