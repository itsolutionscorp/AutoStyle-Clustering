class Bob

  def hey(message)
    if message.nil? || '' == message.strip
      'Fine. Be that way.'
    elsif message.end_with?('?')
      'Sure.'
    elsif message.upcase == message
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

end
