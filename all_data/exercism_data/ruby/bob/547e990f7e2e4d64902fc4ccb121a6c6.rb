class Bob

  def hey(sentence)
    if shouting?(sentence)
      'Woah, chill out!'
    elsif question?(sentence)
      "Sure."
    elsif silence?(sentence)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

  def contains_letters?(sentence)
    !!(sentence =~ /[a-zA-Z]/)
  end

  def uppercase?(sentence)
    sentence == sentence.upcase
  end

  def shouting?(sentence)
    contains_letters?(sentence) && uppercase?(sentence)
  end

  def question?(sentence)
    sentence.end_with?("?")
  end

  def silence?(sentence)
    sentence.strip == ''
  end
end
