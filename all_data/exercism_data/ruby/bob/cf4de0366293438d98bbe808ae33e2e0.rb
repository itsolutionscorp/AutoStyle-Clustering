class Bob

  def hey(message)
    return 'Fine. Be that way!' if message.strip.empty?

    if message.upcase == message && message.match(/[A-z]/)
      'Woah, chill out!'
    elsif(message[-1, 1] == '?')
      'Sure.'
    else
      'Whatever.'
    end
  end

end
