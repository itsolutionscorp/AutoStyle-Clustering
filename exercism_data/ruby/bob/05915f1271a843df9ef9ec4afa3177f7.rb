class Bob

  def hey phrase
    return 'Fine. Be that way!' if is_silence? phrase
    return 'Woah, chill out!' if is_shouting? phrase
    return 'Sure.' if is_question? phrase
    'Whatever.'
  end

  def is_silence? phrase
    phrase.strip.empty?
  end

  def is_shouting? phrase
    phrase.upcase === phrase
  end

  def is_question? phrase
    phrase.end_with? '?'
  end

end
