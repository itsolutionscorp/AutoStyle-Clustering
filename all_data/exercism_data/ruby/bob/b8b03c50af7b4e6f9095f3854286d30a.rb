class Bob
  def hey(message)
    return 'Woah, chill out!' if shouting?(message)
    return 'Sure.' if asking?(message)
    return 'Fine. Be that way!' if nothing_said?(message)
    'Whatever.'
  end

  private
  def shouting?(message)
    message == message.upcase && message =~ /[A-Z]/
  end

  def asking?(message)
    message.end_with?('?')
  end

  def nothing_said?(message)
    message.strip.empty?
  end
end
