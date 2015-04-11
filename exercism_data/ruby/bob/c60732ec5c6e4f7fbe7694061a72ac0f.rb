class Bob
  SILENT_TREATEMENT = 'Fine. Be that way!'
  BACK_OFF = 'Woah, chill out!'
  AGREE = 'Sure.'
  SHRUG = 'Whatever.'

  def hey(sentence)
    return SILENT_TREATEMENT if is_silent?(sentence)
    return BACK_OFF if is_shouting?(sentence)
    return AGREE if is_question?(sentence)
    SHRUG
  end

  private

  def is_silent?(sentence)
    sentence.strip.empty?
  end

  def is_shouting?(sentence)
    sentence.upcase == sentence
  end

  def is_question?(sentence)
    sentence.end_with?('?')
  end
end
