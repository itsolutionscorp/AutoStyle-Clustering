class Bob

  def hey(words)
    if is_silent?(words)
      "Fine. Be that way!"
    elsif is_shout?(words)
      "Woah, chill out!"
    elsif is_question?(words)
      "Sure."
    else
      "Whatever."
    end
  end

  def is_silent?(words)
    words.nil? || words.strip.empty?
  end

  def is_shout?(words)
    words.upcase == words
  end

  def is_question?(words)
    words[-1] == "?"
  end
end
