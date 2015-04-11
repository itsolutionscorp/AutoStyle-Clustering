class Bob

  def hey(words)
    words = words.gsub(" ", "")

    if silence?(words)
      "Fine. Be that way!"
    elsif question?(words)
      "Sure."
    elsif shout?(words)
      "Woah, chill out!"
    else
      "Whatever."
    end
  end

  def silence?(words)
    words.size == 0
  end

  def question?(words)
    !shout?(words) && words[-1] == "?"
  end

  def shout?(words)
    !silence?(words) && words.upcase == words
  end

end
