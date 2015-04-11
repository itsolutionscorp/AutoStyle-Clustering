class Bob

  def hey(message)
    if message.strip.empty?
      'Fine. Be that way!'
    elsif message.upcase == message && message.match(/[A-z]/)
      'Woah, chill out!'
    elsif message.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end

end
