class Bob

  def hey(sentence)
    if silence?(sentence)
      "Fine. Be that way!"
    elsif shouting?(sentence)
      "Woah, chill out!"
    elsif question?(sentence)
      "Sure."
    else
      "Whatever."
    end
  end

 protected

  def silence?(sentence)
    sentence.strip.empty?
  end

  def has_words?(sentence)
    sentence.match(/[a-zA-Z]/)
  end

  def question?(sentence)
    sentence.end_with? "?"
  end

  def shouting?(sentence)
    has_words?(sentence) && sentence.upcase == sentence
  end

end
