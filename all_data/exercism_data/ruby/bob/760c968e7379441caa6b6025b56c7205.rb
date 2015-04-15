class Bob
  def hey(sentence)
    if silence? sentence
      response = "Fine. Be that way!"
    elsif shouting? sentence
      response = "Woah, chill out!"
    elsif question? sentence
      response = "Sure."
    else
      response = "Whatever."
    end
    return response
  end

  private

  def silence?(sentence)
    sentence.lstrip.empty?
  end

  def shouting?(sentence)
    sentence.upcase == sentence
  end

  def question?(sentence)
    sentence.end_with? "?"
  end
end
