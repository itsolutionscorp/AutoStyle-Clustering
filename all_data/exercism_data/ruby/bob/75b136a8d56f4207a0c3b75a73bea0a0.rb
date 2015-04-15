class Bob

  def hey(message)
    return 'Fine. Be that way.' if silence? message
    return 'Sure.' if asking? message
    return 'Woah, chill out!' if shouting? message
    return 'Whatever.'
  end

  private
  def shouting?(message)
    message.match(/[A-Z]{2,}/)
  end

  def asking?(message)
    message.end_with?('?')
  end
 
  def silence?(message)
    !message || message.empty?
  end
end
