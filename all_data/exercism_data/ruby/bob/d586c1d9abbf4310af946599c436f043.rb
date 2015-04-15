class Bob
  def hey(sentence)
    case
    when is_shouting?(sentence)
      'Whoa, chill out!'
    when is_a_question?(sentence)
      'Sure.'
    when is_silence?(sentence)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  def is_shouting?(sentence)
    return false if is_only_numbers? sentence
    sentence.upcase == sentence
  end

  def is_a_question?(sentence)
    sentence.end_with? '?'
  end

  def is_only_numbers?(sentence)
    sentence.swapcase == sentence
  end

  def is_silence?(sentence)
    sentence.strip == ''
  end
end
