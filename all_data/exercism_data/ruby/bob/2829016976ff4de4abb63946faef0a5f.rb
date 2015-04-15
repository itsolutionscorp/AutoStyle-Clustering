class Bob
  def hey(sentence)

    if silence?(sentence)
      return 'Fine. Be that way!'
    elsif screaming?(sentence)
      return 'Woah, chill out!'
    elsif question?(sentence)
      return 'Sure.'
    end

    'Whatever.'
  end

  private
  def silence?(sentence)
    sentence.strip.empty?
  end

  def question?(sentence)
    sentence.end_with? '?'
  end

  def screaming?(sentence)
    sentence == sentence.upcase && contains_letters(sentence)
  end

  def contains_letters(sentence)
    !(sentence =~ /^((?![a-zA-Z]).)*$/)
  end


end
