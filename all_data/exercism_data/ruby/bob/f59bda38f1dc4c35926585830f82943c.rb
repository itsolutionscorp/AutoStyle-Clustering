class Bob
  def hey phrase
    if quiet? phrase
      'Fine. Be that way.'
    elsif shouting? phrase
      'Woah, chill out!'
    elsif question? phrase
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def shouting? phrase
    phrase.upcase == phrase unless quiet? phrase
  end

  def question? phrase
    phrase.end_with? '?'
  end

  def quiet? phrase
    phrase.nil? || phrase.empty?
  end
end
