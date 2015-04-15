class Bob

  def hey phrase
    case
    when (silent? phrase)
      'Fine. Be that way.'
    when (shouting? phrase)
      'Woah, chill out!'
    when (question? phrase)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silent? phrase
    phrase.to_s.empty?
  end

  def shouting? phrase
    phrase == phrase.upcase
  end

  def question? phrase
    phrase.end_with? '?'
  end
end
