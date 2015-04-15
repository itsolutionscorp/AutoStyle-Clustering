class Bob
  def hey(word)
    if silence?(word)
      "Fine. Be that way."
    elsif yell?(word)
      "Woah, chill out!"
    elsif question?(word)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def silence?(word)
    String(word).empty?
  end

  def question?(word)
    word.end_with?("?")
  end

  def yell?(word)
    word == word.upcase
  end
end
