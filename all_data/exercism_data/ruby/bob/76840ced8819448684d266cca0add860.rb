class Bob
  def hey(message)
    return 'Fine. Be that way!' if message.nil? || message.empty?
    return 'Woah, chill out!' if message.upcase == message
    return 'Sure.' if message.chars.last == '?'
    'Whatever.'
  end
end
