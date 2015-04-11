class Bob
  def hey(sentence)
    if saying_nothing?(sentence)
      'Fine. Be that way!'
    elsif yelling?(sentence)
      'Woah, chill out!'
    elsif asking?(sentence)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def saying_nothing?(sentence)
    sentence.strip.empty?
  end

  def yelling?(sentence)
    sentence == sentence.upcase
  end

  def asking?(sentence)
    sentence.end_with?('?')
  end
end
