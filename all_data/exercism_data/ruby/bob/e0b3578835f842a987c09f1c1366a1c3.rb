class Bob
  def hey(message)
    return 'Fine. Be that way!' if message.to_s.strip == ""
    return 'Woah, chill out!' if message.upcase == message
    return 'Sure.' if message.match /.*\?$/
    'Whatever.'
  end
end
