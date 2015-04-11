class Bob
  def hey(phrase)
    phrase.strip!
    if is_silent phrase
      return 'Fine. Be that way!'
    end
    if is_shouting phrase
      return 'Woah, chill out!'
    end
    if is_question phrase
      return 'Sure.'
    end

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
end
