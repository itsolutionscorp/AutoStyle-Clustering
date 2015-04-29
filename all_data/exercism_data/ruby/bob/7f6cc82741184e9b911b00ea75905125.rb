class Bob
  def hey message
    return 'Fine. Be that way!' if message.strip.empty?
    return 'Woah, chill out!' if shouting? message
    return 'Sure.' if message.end_with?('?')
    'Whatever.'
  end

  private

  def shouting? message
    message == message.upcase
  end
end
