class Bob
  def hey(message = nil)
    return 'Fine. Be that way!' if message.nil? || message.strip.empty?
    return 'Woah, chill out!'   if message.upcase == message
    return 'Sure.'              if message.end_with? '?'
    'Whatever.'
  end
end
