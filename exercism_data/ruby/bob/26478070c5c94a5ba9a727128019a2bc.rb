class Bob

  def hey(message)
    return 'Fine. Be that way!' if saying_nothing?(message)
    return 'Woah, chill out!' if yelling?(message)
    return 'Sure.' if asking_a_question?(message)
    return 'Whatever.'
  end

private
  
  def saying_nothing?(message)
    message.strip.empty?
  end

  def asking_a_question?(message)
    message.end_with?('?')
  end

  def yelling?(message)
    message.upcase == message
  end

end
