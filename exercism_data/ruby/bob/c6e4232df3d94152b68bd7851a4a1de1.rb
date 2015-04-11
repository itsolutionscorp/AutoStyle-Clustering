class Bob
  def hey(phrase)
    return 'Fine. Be that way!' if is_silent?(phrase)
    return 'Woah, chill out!'   if is_shouting?(phrase)
    return 'Sure.'              if is_asking?(phrase)
    'Whatever.'
  end

  def is_silent?(phrase)
    phrase.strip.empty?
  end

  def is_shouting?(phrase)
    phrase == phrase.upcase && phrase =~ /[A-Za-z]/
  end

  def is_asking?(phrase)
    phrase.end_with?('?')
  end
end
