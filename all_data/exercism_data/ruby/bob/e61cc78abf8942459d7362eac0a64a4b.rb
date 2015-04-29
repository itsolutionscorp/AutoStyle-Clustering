class Bob
  def hey(something)
    @phrase = something.strip
    if silence?
      "Fine. Be that way!"
    elsif yelling?
      "Woah, chill out!"
    elsif question?
      "Sure."
    else 
      "Whatever."
    end
  end

  def silence?
    @phrase.empty?
  end

  def question?
    @phrase.end_with?("?")
  end

  def yelling?
    @phrase.upcase == @phrase
  end
end
