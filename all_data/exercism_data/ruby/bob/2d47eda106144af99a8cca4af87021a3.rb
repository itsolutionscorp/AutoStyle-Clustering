class Bob
  def hey(phrase)
    if    shouting? phrase
      "Woah, chill out!"
    elsif question? phrase
      "Sure."
    elsif silence?  phrase
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

  def shouting?(phrase)
    phrase == phrase.upcase && phrase.match(/[a-z]/i)
  end

  def question?(phrase)
    phrase.end_with?('?')
  end

  def silence?(phrase)
    phrase.match(/\A\s*\z/)
  end
end
