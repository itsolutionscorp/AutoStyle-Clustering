class Bob
  def hey(message)
    if message.strip.empty?
      'Fine. Be that way!'
    elsif message.match(/[[:upper:]]{2,}/) && message.upcase == message
      'Woah, chill out!'
    elsif message.match(/\?\z/)
      'Sure.'
    else
      'Whatever.'
    end
  end
end
