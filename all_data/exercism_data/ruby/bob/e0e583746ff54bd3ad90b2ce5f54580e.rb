class Bob
  def hey(message)
    return 'Whatever.' unless message.is_a? String
    message.strip!
    return 'Fine. Be that way!' if silence? message
    return 'Woah, chill out!' if yelling? message
    return 'Sure.' if asking? message
    'Whatever.'
  end
  def silence? message
    message.empty?
  end
  def yelling? message
    message.upcase == message
  end
  def asking? message
    message.end_with? '?'
  end
end
