class Bob

  RESPONSE_TO_SILENCE = "Fine. Be that way!"
  RESPONSE_TO_YELLING = "Woah, chill out!"
  RESPONSE_TO_QUERIES = "Sure."
  RESPONSE_TO_BANALITY = "Whatever."

  def hey(message)
    if silence? message
      RESPONSE_TO_SILENCE
    elsif yelling? message
      RESPONSE_TO_YELLING
    elsif question? message
      RESPONSE_TO_QUERIES
    else
      RESPONSE_TO_BANALITY
    end
  end

  def silence?(message)
    message.match /^\s*$/
  end

  def yelling?(message)
    message.match /^[^a-z]+$/
  end

  def question?(message)
    message.match /\?$/
  end

  private :silence?, :yelling?, :question?

end
