class Bob
  def hey(message)
    return 'Fine. Be that way!' if are_the_quiet?(message)
    return 'Woah, chill out!' if are_they_shouting_to_me?(message)
    return 'Sure.' if are_the_asking?(message)
    'Whatever.'
  end

  protected

  def are_the_quiet?(message)
    message.to_s.strip == ""
  end

  def are_they_shouting_to_me?(message)
    message.upcase == message
  end

  def are_the_asking?(message)
    message.end_with? '?'
  end
end
