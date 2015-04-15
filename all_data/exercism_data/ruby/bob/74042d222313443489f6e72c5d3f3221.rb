class Bob

  def hey(sentence)
    @sentence = sentence

    return 'Fine. Be that way!' if silence?
    return 'Sure.' if end_with_question_mark? && (contains_lowercase || contains_digits)
    return 'Woah, chill out!' if contains_uppercase && !contains_lowercase
    'Whatever.'
  end

  def silence?
    sentence.strip.length == 0
  end

  def end_with_question_mark?
    sentence.end_with?('?')
  end

  def contains_lowercase
    sentence.match(/[a-z]+/)
  end

  def contains_uppercase
    sentence.match(/[A-Z]+/)
  end

  def contains_digits
    sentence.match(/[\d]+/)
  end

  def sentence
    @sentence
  end
end
