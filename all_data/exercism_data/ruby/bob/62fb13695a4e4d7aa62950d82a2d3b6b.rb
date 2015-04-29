class Bob
  def hey(message)
    message = message.strip

    if message.empty?
      'Fine. Be that way!'
    elsif message.upcase == message
      'Woah, chill out!'
    elsif message.end_with? '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
