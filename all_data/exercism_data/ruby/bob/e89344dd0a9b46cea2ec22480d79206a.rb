class Bob

  def hey(phrase)
    clean_phrase = phrase.strip
    response(clean_phrase)
  end

  private

  def response(phrase)
    if shouting?(phrase)
      "Whoa, chill out!"
    elsif question?(phrase)
      "Sure."
    elsif silence?(phrase)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  def shouting?(phrase)
    phrase == phrase.upcase && phrase =~ (/[a-zA-Z]/)
  end

  def question?(phrase)
    phrase.end_with?("?")
  end

  def silence?(phrase)
    phrase.empty?
  end
end
