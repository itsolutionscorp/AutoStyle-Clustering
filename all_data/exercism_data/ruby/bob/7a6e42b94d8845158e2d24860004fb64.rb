class Bob

  SILENCE_RESPONSE = "Fine. Be that way!"
  YELLING_RESPONSE = "Woah, chill out!"
  AFFIRMATIVE = "Sure."
  AMBIVALENT = "Whatever."

  def hey input
    return SILENCE_RESPONSE if is_silence? input
    return YELLING_RESPONSE if is_yelling_coherent? input
    return AFFIRMATIVE if is_question? input
    AMBIVALENT
  end

  def is_silence? input
    input.strip.empty?
  end
  private :is_silence?

  def is_yelling_coherent? input
    is_yelling?(input) && says_something?(input)
  end
  private :is_yelling_coherent?

  def is_yelling? input
    input.upcase == input
  end
  private :is_yelling?

  def says_something? input
    input.match(/([a-zA-Z]+.)+/)
  end
  private :says_something?

  def is_question? input
    input[-1] == '?'
  end
  private :is_question?

end
