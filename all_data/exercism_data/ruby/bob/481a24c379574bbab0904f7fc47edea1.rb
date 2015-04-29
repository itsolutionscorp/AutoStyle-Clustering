class Bob
  def hey(phrase)
    if !phrase || phrase.empty?
      'Fine. Be that way.'
    elsif phrase.upcase == phrase
      'Woah, chill out!'
    elsif phrase.end_with? '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
