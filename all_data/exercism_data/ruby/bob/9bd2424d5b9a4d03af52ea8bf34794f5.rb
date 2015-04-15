class Bob

  RESPONSES = {
    interruption: "Fine. Be that way!",
    shout:        "Woah, chill out!",
    question:     "Sure.",
    default:      "Whatever."
  }

  def hey(phrase)
    RESPONSES[classify(phrase)]
  end

  private

  def classify(phrase)
    return :interruption if is_interruption?(phrase)
    return :shout        if is_shout?(phrase)
    return :question     if is_question?(phrase)
    :default
  end

  def is_interruption?(phrase)
    phrase.strip.empty?
  end

  def is_shout?(phrase)
    phrase == phrase.upcase
  end

  def is_question?(phrase)
    phrase[-1,1] == '?'
  end

end
