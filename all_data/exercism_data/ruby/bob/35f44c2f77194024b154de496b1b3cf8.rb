class Bob
  def hey(message)
    message = message.to_s
    return 'Sure.'              if message.end_with? '?'
    return 'Fine. Be that way.' if message.length == 0
    return 'Woah, chill out!'   if message.upcase == message
    return 'Whatever.'
  end
end
