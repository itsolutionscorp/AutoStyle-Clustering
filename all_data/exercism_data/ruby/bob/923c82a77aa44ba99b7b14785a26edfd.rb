class Bob

  def hey(value)
    return 'Fine. Be that way!' if silence?(value)
    return 'Woah, chill out!' if shouting?(value)
    return 'Sure.' if asking?(value)
    'Whatever.'
  end

  private

  def shouting?(value)
    value.upcase == value && value.upcase =~ /[A-Z]/
  end

  def asking?(value)
    value.end_with?('?')
  end

  def silence?(value)
    value.strip == ''
  end

end
