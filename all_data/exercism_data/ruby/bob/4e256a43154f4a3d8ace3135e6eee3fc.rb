class Bob
  def hey(phrase)
    phrase = trim_input phrase
    return 'Fine. Be that way!' if is_silent phrase
    return 'Woah, chill out!' if is_shouting phrase
    return 'Sure.' if is_question phrase

    'Whatever.'
  end

  private
  def is_question(phrase)
    phrase.end_with? '?'
  end

  def is_shouting(phrase)
    phrase.upcase == phrase
  end

  def is_silent(phrase)
    phrase == ''
  end

  def trim_input(phrase)
    phrase.strip
  end
end
