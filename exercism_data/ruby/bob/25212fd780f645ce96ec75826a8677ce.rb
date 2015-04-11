class Bob
  def hey message
    return 'Fine. Be that way!' if silence? message
    return 'Woah, chill out!' if shouting? message
    return 'Sure.' if asking? message
    'Whatever.'
  end

  private

  def silence? message
    message.strip.empty?
  end

  def shouting? message
    message == message.upcase
  end

  def asking? message
    message.end_with?('?')
  end
end
