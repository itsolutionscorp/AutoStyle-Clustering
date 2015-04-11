class Bob

  def hey phrase
    case
    when (silent? phrase)
      'Fine. Be that way.'
    when (shouting? phrase)
      'Woah, chill out!'
    when (asking_a_question? phrase)
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

  def asking_a_question? phrase
    phrase.end_with? '?'
  end
end
