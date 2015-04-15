class Bob
  def hey phrase
    if shouting? phrase
      'Woah, chill out!'
    elsif asking_a_question? phrase
      'Sure.'
    elsif silence? phrase
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  def shouting? phrase
    phrase == phrase.upcase && phrase != phrase.downcase
  end

  def asking_a_question? phrase
    phrase.end_with? '?'
  end

  def silence? phrase
    phrase.strip == ''
  end
end
