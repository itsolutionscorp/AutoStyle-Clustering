class Bob
  def hey(phrase)
    case 
    when is_silent?(phrase)
      'Fine. Be that way.'
    when is_shouting?(phrase)
     "Woah, chill out!"
    when is_question?(phrase)
     'Sure.'
    else
      "Whatever."
    end
  end

  def is_silent?(phrase)
    phrase.empty?
  end

  def is_shouting?(phrase)
    phrase == phrase.upcase
  end

  def is_question?(phrase)
    phrase.end_with?("?")
  end
end
