class Bob
  def hey(message)
    return 'Sure.' if message.match(/\?$/) && !(message == message.upcase)

    return 'Woah, chill out!' if message == message.upcase && message.match(/[A-Z]/)

    return 'Fine. Be that way!' if !(message.match(/(?:[A-Z]|[a-z]|[0-9])/))
    
    return 'Whatever.'
  end
end
