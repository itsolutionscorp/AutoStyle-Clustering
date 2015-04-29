class Bob
  def hey(message)
    return 'Fine. Be that way!' if blank?(message)
    return 'Woah, chill out!' if shouting?(message)
    return 'Sure.' if question?(message)

    'Whatever.'
  end

  private
  def blank?(message)
    message !~ /[^[:space:]]/
  end

  def shouting?(message)
    message.upcase == message
  end

  def question?(message)
    message[-1] == '?'
  end
end
