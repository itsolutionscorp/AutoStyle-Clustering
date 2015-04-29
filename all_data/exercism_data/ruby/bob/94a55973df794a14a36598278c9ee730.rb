class Bob

  def hey(message)
    return 'Fine. Be that way!' if message.nil? || message.strip.empty?
    return 'Woah, chill out!' if message.upcase == message
    return 'Sure.' if message.strip =~ /\?$/
    'Whatever.'
  end

end
