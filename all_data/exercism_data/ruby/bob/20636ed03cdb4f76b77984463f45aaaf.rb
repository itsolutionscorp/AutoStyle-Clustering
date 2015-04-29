class Bob
  def hey(message)
    return 'Woah, chill out!' if message.upcase == message && /[a-z]+/i.match(message)

    return 'Sure.' if message[-1] == '?'

    return 'Fine. Be that way!' if message.strip == ''

    'Whatever.'
  end
end
