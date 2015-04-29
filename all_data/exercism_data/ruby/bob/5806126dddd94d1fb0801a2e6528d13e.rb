class Bob
  def hey(sentence)
    if blank?(sentence)
      return "Fine. Be that way!"
    elsif shouting?(sentence)
      return "Woah, chill out!"
    elsif question?(sentence)
      return "Sure."
    else
      return "Whatever."
    end
  end

  def blank?(sentence)
    return (sentence.nil? or sentence.strip.empty?)
  end

  def shouting?(sentence)
    return sentence == sentence.upcase
  end

  def question?(sentence)
    return sentence[-1] == "?"
  end
end
