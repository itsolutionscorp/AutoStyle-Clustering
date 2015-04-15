class Bob
  def hey(phrase)
    @phrase = phrase
    if blank_phrase?
      'Fine. Be that way.'
    elsif upcase_phrase?
      'Woah, chill out!'
    elsif tell_phrase?
      'Whatever.'
    else
      'Sure.'
    end
  end

protected
  def blank_phrase?
    @phrase.nil? || @phrase.empty?
  end

  def upcase_phrase?
    @phrase.upcase == @phrase
  end

  def tell_phrase?
    @phrase.end_with?('.', '!')
  end
end
