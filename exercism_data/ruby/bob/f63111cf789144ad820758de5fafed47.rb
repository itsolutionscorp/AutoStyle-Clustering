class Bob
  def hey(phrase)
    case 
    when silent?(phrase)
      'Fine. Be that way.'
    when shouting?(phrase)
     "Woah, chill out!"
    when question?(phrase)
     'Sure.'
    else
      "Whatever."
    end
  end

  def silent?(phrase)
    phrase.empty?
  end

  def shouting?(phrase)
    phrase == phrase.upcase
  end

  def question?(phrase)
    phrase.end_with?("?")
  end
end
