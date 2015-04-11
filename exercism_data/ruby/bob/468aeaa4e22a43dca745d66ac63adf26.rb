class Bob
  def hey(message)
    return 'Fine. Be that way!' if empty?(message)
    return 'Woah, chill out!' if yelling?(message)
    return 'Sure.' if question?(message)
    'Whatever.'
  end

  private
  def empty?(message)
    message.nil? || message.empty?
  end

  def yelling?(message)
    message.upcase == message
  end

  def question?(message)
    message[-1] == "?"
  end
end
