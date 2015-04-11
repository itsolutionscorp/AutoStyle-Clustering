class Bob
  def hey(message)
    return 'Fine. Be that way!' if silence?(message)
    return 'Woah, chill out!'   if yelling?(message)
    return 'Sure.'              if question?(message)
    return 'Whatever.'
  end

  private

  def yelling?(message)
    message.upcase == message
  end

  def question?(message)
    message.end_with? '?'
  end

  def silence?(message)
    message.strip.empty?
  end
end
