class Bob

  def hey message
    @msg = message.strip
    return 'Fine. Be that way.' if empty_message?
    return 'Woah, chill out!' if yelling_message?
    return 'Sure.' if asking_message?
    return 'Whatever.' 
  end

  def yelling_message?
    @msg == @msg.upcase
  end

  def asking_message?
    @msg.end_with?('?')
  end

  def empty_message?
    @msg.empty?
  end
end
