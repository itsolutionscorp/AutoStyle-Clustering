class Bob
  def hey(words)
    if silence? words
      "Fine. Be that way!"
    elsif shouting? words
      "Woah, chill out!"
    elsif question? words
      "Sure."
    else
      "Whatever."
    end
  end

  def silence?(words)
    words.nil? || words.strip.empty?
  end

  def shouting?(words)
    words.upcase == words
  end

  def question?(words)
    words.end_with? "?"
  end
end
