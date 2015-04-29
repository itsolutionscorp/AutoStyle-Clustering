class Bob
  attr_reader :phrase

  def hey(phrase)
    @phrase = phrase

    if silence?
      "Fine. Be that way!"
    elsif yelled_at?
      "Woah, chill out!"
    elsif questioned?
      "Sure."
    else
      "Whatever."
    end
  end

  def silence?
    @phrase.nil? || @phrase.empty?
  end

  def yelled_at?
    @phrase.match(/\p{Lower}/).nil?
  end
  
  def questioned?
    @phrase.end_with? "?"
  end
end
