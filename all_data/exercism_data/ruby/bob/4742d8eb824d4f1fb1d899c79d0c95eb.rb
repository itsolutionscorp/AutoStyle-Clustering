class Bob
  def hey(sentence)
    if blank?(sentence)
      return "Fine. Be that way!"
    elsif all_caps?(sentence)
      return "Woah, chill out!"
    elsif statement?(sentence)
      return "Whatever."
    elsif question?(sentence)
      return "Sure."
    end
  end

  def blank?(sentence)
    return (sentence.nil? or sentence.strip.empty?)
  end

  def all_caps?(sentence)
    return sentence == sentence.upcase
  end

  def statement?(sentence)
    return (sentence[-1] == "." or sentence[-1] == "!")
  end

  def question?(sentence)
    return sentence[-1] == "?"
  end
end
