class Bob
  attr_reader :words

  def hey(words)
    @words = words
    if shouting
      "Woah, chill out!"
    elsif question
      "Sure."
    elsif empty
      "Fine. Be that way."
    else 
      "Whatever."
    end
  end

  def shouting
    words == words.upcase && words != ''
  end

  def question
    words.end_with? '?'
  end

  def empty
    words.empty?
  end

end
