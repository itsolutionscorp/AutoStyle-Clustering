class Bob
  def hey(phrase)
    @phrase = phrase
    if phrase_is_empty?
      'Fine. Be that way!'
    elsif phrase_is_yelling?
      'Woah, chill out!'
    elsif phrase_is_questioning?
      'Sure.'
    else
      "Whatever."
    end
  end

  private
  def phrase_is_empty?
    @phrase.strip.empty?
  end

  def phrase_is_yelling?
    @phrase == @phrase.upcase && @phrase.scan(/[a-zA-Z]/).any?
  end

  def phrase_is_questioning?
    @phrase[-1,1] == '?'
  end
end
