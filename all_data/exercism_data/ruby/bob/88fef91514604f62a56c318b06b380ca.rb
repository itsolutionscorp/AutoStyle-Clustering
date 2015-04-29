class Bob
  attr_reader :words

  def hey(words)
    @words = words
    if shouting?
      "Woah, chill out!"
    elsif asking?
      "Sure."
    elsif silence?
      "Fine. Be that way."
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
