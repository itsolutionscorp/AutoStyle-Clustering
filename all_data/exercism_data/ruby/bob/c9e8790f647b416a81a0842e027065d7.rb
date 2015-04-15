class Bob
  attr_reader :words

  def hey(words)

    if silence?
      "Fine. Be that way."
    elsif shouting?
      "Woah, chill out!"
    elsif asking?
      "Sure."
    else 
      "Whatever."
    end
  end

  def shouting?
    words == words.upcase && words != ''
  end

  def asking?
    words.end_with? '?'
  end

  def silence?
    words.empty?
  end

end
