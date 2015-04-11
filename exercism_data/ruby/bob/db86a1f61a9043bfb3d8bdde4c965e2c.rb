class Bob
  def hey(sentence)
    if blank?(sentence)
      "Fine. Be that way!"
    elsif shouting?(sentence)
      "Woah, chill out!"
    elsif question?(sentence)
      "Sure."
    else
      "Whatever."
    end
  end
  private
  def blank?(sentence)
    (sentence.nil? or sentence.strip.empty?)
  end

  def shouting?(sentence)
    sentence == sentence.upcase
  end

  def question?(sentence)
    sentence.end_with?("?")
  end
end
