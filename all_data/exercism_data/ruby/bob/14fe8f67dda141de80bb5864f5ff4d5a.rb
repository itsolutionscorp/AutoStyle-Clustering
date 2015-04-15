class Bob
  def hey(message)
    return 'Fine. Be that way!' if message.scan(/[a-zA-Z0-9]/).empty?
    return 'Whoa, chill out!' if message == message.upcase and not message.scan(/[a-zA-Z]/).empty?
    return 'Sure.' if message[-1] == '?'
    return 'Whatever.'
  end
end
