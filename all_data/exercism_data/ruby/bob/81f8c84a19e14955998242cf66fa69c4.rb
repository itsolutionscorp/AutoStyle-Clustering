class Bob
  def hey(message)
    return 'Fine. Be that way!' if saying_nothing?(message)
    return 'Woah, chill out!' if shouting?(message)
    return 'Sure.' if asking?(message)
    'Whatever.'
  end

  private
  def saying_nothing?(message)
    message.strip.empty?
  end

  def shouting?(message)
    message.upcase == message && contains_alphabet_character?(message)
  end

  def contains_alphabet_character?(message)
    !!message.index(/\p{Alpha}/)
  end

  def asking?(message)
    message[-1] == '?'
  end
end
