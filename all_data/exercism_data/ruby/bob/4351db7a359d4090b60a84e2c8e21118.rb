class Bob
  def hey(message)
    return 'Woah, chill out!'   if message == message.upcase && message != message.downcase
    return 'Sure.'              if message.end_with? '?'
    return 'Fine. Be that way!' if message.strip.empty?
    return 'Whatever.'
  end
end
# O.o
