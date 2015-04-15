class Bob
  def hey(message)
    return 'Fine. Be that way!' if silence?(message.to_s)
    return 'Woah, chill out!' if yelling?(message.to_s)
    return 'Sure.' if question?(message.to_s)
    'Whatever.'
  end

  private

  def silence?(message)
    message.strip.empty?
  end

  def yelling?(message)
    message == message.upcase
  end

  def question?(message)
    message.end_with?('?')
  end
end
