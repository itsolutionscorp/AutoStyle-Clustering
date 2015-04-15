class Bob

  def hey(words)
    words.gsub!(/\n/, " ")
    words.lstrip!
    if silence?(words)
      "Fine. Be that way!"
    elsif screaming?(words) && only_letters?(words)
      "Woah, chill out!"
    elsif question?(words)
      "Sure."
    else
      "Whatever."
    end
  end

  private 
  def silence?(words)
    words.empty?
  end

  def screaming?(words)
    words == words.upcase
  end

  def only_letters?(words)
    words.match(/[a-zA-Z]+/)
  end

  def question?(words)
     words.end_with? '?'
  end

end
