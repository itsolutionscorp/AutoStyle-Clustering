class Bob

  def hey(phrase)
    if empty?(phrase)
      'Fine. Be that way!'
    elsif yelling?(phrase)
      'Woah, chill out!'
    elsif question?(phrase)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def empty?(phrase)
    if phrase.match(/^\s+$/)
      true
    elsif phrase.empty?
      true
    end
  end

  def yelling?(phrase)
    phrase == phrase.upcase
  end

  def question?(phrase)
    phrase.end_with?('?')
  end

end