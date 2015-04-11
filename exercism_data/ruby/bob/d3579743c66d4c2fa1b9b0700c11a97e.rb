class Bob
  def hey(phrase)
    return 'Fine. Be that way!' if phrase.strip.empty?
    return 'Woah, chill out!' if phrase.upcase == phrase
    return 'Sure.' if phrase.end_with? '?'
    'Whatever.'
  end
end
