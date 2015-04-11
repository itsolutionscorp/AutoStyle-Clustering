class Bob
  def hey(words)
    if is_silence? words
      "Fine. Be that way!"
    elsif is_shouting? words
      "Woah, chill out!"
    elsif is_a_question? words
      "Sure."
    else
      "Whatever."
    end
  end

  def is_silence?(words)
    if words.nil?
      true
    elsif words.strip.empty?
      true
    else
      false
    end
  end

  def is_shouting?(words)
    words.upcase == words
  end

  def is_a_question?(words)
    words.end_with? "?"
  end
end
