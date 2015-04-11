class Bob
  def hey(message)
    return 'Fine. Be that way.' if silence?(message)
    return 'Woah, chill out!' if shouting?(message)
    return 'Sure.' if question?(message)
    'Whatever.'
  end

  private

  def silence?(message)
    message.nil? or message.empty?
  end

  def shouting?(message)
    message == message.upcase
  end

  def question?(message)
    message.end_with? '?'
  end
end
