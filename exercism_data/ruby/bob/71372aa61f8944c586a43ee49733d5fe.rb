class Bob

  def hey(phrase)
    if silent?(phrase)
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
  def silent?(phrase)
    phrase.nil? || phrase.strip.empty?
  end

  def yelling?(phrase)
    phrase == phrase.upcase
  end

  def question?(phrase)
    phrase.end_with?('?')
  end
end
