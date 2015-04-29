class Bob

  def hey(words)

    if silence?(words)
      "Fine. Be that way."
    elsif shouting?(words)
      "Woah, chill out!"
    elsif asking?(words)
      "Sure."
    else 
      "Whatever."
    end
  end

  def shouting?(words)
    words == words.upcase
  end

  def asking?(words)
    words.end_with? '?'
  end

  def silence?(words)
    words.empty?
  end

end
