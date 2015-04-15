class Bob

  def hey(message)
    return 'Woah, chill out!' if message.upcase == message && message =~ /[A-Z]+/
    return 'Sure.' if message.end_with?('?')
    return 'Fine. Be that way!' if message.strip.empty?
    'Whatever.'
  end

end
