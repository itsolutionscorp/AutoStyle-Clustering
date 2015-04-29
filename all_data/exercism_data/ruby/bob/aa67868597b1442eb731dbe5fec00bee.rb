class Bob

  SILENCE = /^\s*$/
  QUESTION = /\?$/
  YELL = /^[^a-z]*$/

  def hey(text)
    case text
    when nil, SILENCE
      respond_to_silence
    when YELL
      respond_to_yell
    when QUESTION
      respond_to_question
    else
      respond_to_anything
    end
  end

  def respond_to_silence
    "Fine. Be that way!"
  end

  def respond_to_yell
    "Woah, chill out!"
  end

  def respond_to_question
    "Sure."
  end

  def respond_to_anything
    "Whatever."
  end

end
