class Bob
  def hey phrase
    return "Fine. Be that way." if silent? phrase
    return "Woah, chill out!" if shouting? phrase
    return "Sure." if asking? phrase 
    "Whatever."
  end

  private
  def asking? phrase
    phrase.end_with? '?'
  end

  def shouting? phrase
    phrase.upcase == phrase
  end

  def silent? phrase
    phrase.nil? || phrase.empty?
  end
end
