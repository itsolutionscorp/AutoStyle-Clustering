class Bob
  def hey(sentence)
    if saying_nothing?(sentence)
      return 'Fine. Be that way!'
    end

    if yelling?(sentence)
      return 'Woah, chill out!'
    end

    if asking?(sentence)
       return 'Sure.'
    end

    'Whatever.'
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
