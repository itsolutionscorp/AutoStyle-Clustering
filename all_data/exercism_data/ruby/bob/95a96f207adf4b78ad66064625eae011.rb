class Bob
  def hey(phrase)
    case phrase
    when silence?
      "Fine. Be that way!"
    when shouting?
      "Woah, chill out!"
    when question?
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def silence?
    ->(phrase) { phrase.strip.empty? }
  end

  def shouting?
    ->(phrase) { phrase == phrase.upcase }
  end

  def question?
    ->(phrase) { phrase.end_with? '?' }
  end
end
