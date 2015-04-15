class Bob

  def hey phrase
    case
    when (nil_or_empty? phrase)
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

  def nil_or_empty? phrase
    phrase.to_s == ''
  end

  def shouting? phrase
    phrase =~ /[A-Z]{2,}/
  end

  def asking_a_question? phrase
    phrase =~ /\?$/
  end
end
