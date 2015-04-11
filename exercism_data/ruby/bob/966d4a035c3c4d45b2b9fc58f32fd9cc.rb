class Bob

  def hey(message)
    if message.nil? || '' == message.strip
      'Fine. Be that way.'
    elsif '?' == message[-1, 1]
      'Sure.'
    elsif message.upcase == message
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

end
