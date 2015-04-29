class Bob
  def hey(phrase)
    if blank_phrase?(phrase)
      'Fine. Be that way.'
    elsif upcase_phrase?(phrase)
      'Woah, chill out!'
    elsif tell_phrase?(phrase)
      'Whatever.'
    else
      'Sure.'
    end
  end

protected
  def blank_phrase?(phrase)
    phrase.nil? || phrase.empty?
  end

  def upcase_phrase?(phrase)
    phrase.upcase == phrase
  end

  def tell_phrase?(phrase)
    phrase.end_with?('.', '!')
  end
end
