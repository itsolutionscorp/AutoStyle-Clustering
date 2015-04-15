class Bob

  def hey(phrase)
    if blank?(phrase)
      'Fine. Be that way!'
    elsif all_caps?(phrase)
      'Woah, chill out!'
    elsif is_question?(phrase)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def blank?(phrase)
    phrase.nil? || phrase.empty?
  end

  def all_caps?(phrase)
    phrase == phrase.upcase
  end

  def is_question?(phrase)
    phrase.end_with?('?')
  end
end
