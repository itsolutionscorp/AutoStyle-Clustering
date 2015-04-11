class Bob
  def hey(message)
    if message.strip.empty?
      'Fine. Be that way!'
    elsif message.upcase == message
      'Woah, chill out!'
    elsif message.strip.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
