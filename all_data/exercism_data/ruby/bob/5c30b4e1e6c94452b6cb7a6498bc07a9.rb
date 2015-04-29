class Bob
  def hey(phrase)
    case phrase
    when empty?
      'Fine. Be that way!'
    when question?
      'Sure.'
    when shout?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private
  def empty?
    ->(phrase) { phrase.strip.empty? }
  end

  def shout?
    ->(phrase) { phrase.upcase == phrase }
  end

  def question?
    ->(phrase) { phrase.end_with? '?' }
  end

end
