class Bob
  def hey message
    @message = message
    return 'Fine. Be that way!' if silence?
    return 'Woah, chill out!' if shouting?
    return 'Sure.' if asking?
    'Whatever.'
  end

  private

  def silence?
    @message.strip.empty?
  end

  def shouting?
    @message == @message.upcase
  end

  def asking?
    @message.end_with?('?')
  end
end
