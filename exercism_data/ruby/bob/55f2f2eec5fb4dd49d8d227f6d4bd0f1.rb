class Bob

  def hey(phrase)
    phrase.strip!
    response(phrase)
  end

  private

  def response(phrase)
    if all_caps?(phrase)
      "Whoa, chill out!"
    elsif question?(phrase)
      "Sure."
    elsif silence?(phrase)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  def all_caps?(phrase)
    phrase == phrase.upcase && phrase =~ (/[a-zA-Z]/)
  end

  def question?(phrase)
    phrase.end_with?("?")
  end

  def silence?(phrase)
    phrase.empty?
  end
end
