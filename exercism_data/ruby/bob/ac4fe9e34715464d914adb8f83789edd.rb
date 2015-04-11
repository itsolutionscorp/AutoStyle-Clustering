class Bob
  def hey(sentence)
    if silence?(sentence)
      'Fine. Be that way.'
    elsif yelling?(sentence)
      'Woah, chill out!'
    elsif question?(sentence)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silence?(sentence)
    sentence.to_s.empty?
  end

  def yelling?(sentence)
    sentence.upcase == sentence
  end

  def question?(sentence)
    sentence.end_with('?')
  end
end
