class Bob

  def hey(text)
    @text = text.strip
    return shouting_response if sounds_like_shouting?
    return question_response if sounds_like_a_question?
    return silence_response if sounds_like_silence?
    return default_response
  end

  private

  def shouting_response
    'Woah, chill out!'
  end

  def question_response
    'Sure.'
  end

  def silence_response
    "Fine. Be that way!"
  end

  def default_response
    "Whatever."
  end

  def sounds_like_shouting?
    if is_uppercase? && includes_characters?
      true
    else
      false
    end
  end

  def sounds_like_silence?
    if @text.empty?
      true
    else
      false
    end
  end

  def sounds_like_a_question?
    @text.end_with?("?")
  end

  def includes_characters?
    if @text.upcase != @text.downcase
      true
    else
      false
    end
  end

  def is_uppercase?
    if @text == @text.upcase
      true
    else
      false
    end
  end

end
