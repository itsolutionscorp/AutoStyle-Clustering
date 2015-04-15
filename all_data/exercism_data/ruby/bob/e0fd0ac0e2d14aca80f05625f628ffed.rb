class Bob
  def hey(phrase)
    @phrase = phrase
    if empty_phrase?
      'Fine. Be that way.'
    elsif yelled_phrase?
      'Woah, chill out!'
    elsif tell_phrase?
      'Whatever.'
    else # question_phrase?
      'Sure.'
    end
  end

protected
  def empty_phrase?
    @phrase.nil? || @phrase.empty?
  end

  def yelled_phrase?
    @phrase.upcase == @phrase
  end

  def tell_phrase?
    @phrase.end_with?('.', '!')
  end
end
