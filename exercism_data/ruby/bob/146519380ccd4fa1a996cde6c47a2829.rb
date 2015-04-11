class Bob
  def hey(message)
    return 'Fine. Be that way.' unless message && message.length > 0
    return 'Sure.' if message.match(/\?$/)
    return 'Woah, chill out!' if message.match(/[A-Z]{2,}/)
    return 'Whatever.'
  end
end
