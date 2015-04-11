class Bob
  def hey(message)
    return 'Fine. Be that way!' if empty?(message)
    return 'Woah, chill out!' if yelling?(message)
    return 'Sure.' if question?(message)
    return 'Whatever.'
  end

private

  def yelling?(message)
    all_upcase?(message) && has_letters?(message)
  end

  def has_letters?(message)
    message =~/[a-z]/i
  end

  def all_upcase?(message)
    message ==  message.upcase
  end

  def question?(message)
    message.end_with? '?'
  end

  def empty?(message)
    message !~ /\S/
  end
end
