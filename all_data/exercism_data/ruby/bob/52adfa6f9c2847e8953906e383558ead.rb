class Bob

  RESPONSE_TO_SILENCE = "Fine. Be that way!"
  RESPONSE_TO_YELLING = "Woah, chill out!"
  RESPONSE_TO_QUERIES = "Sure."
  RESPONSE_TO_BANALITY = "Whatever."

  def hey(message)
    if isSilence? message
      RESPONSE_TO_SILENCE
    elsif isYelling? message
      RESPONSE_TO_YELLING
    elsif isQuestion? message
      RESPONSE_TO_QUERIES
    else
      RESPONSE_TO_BANALITY
    end
  end

  def isSilence?(message)
    message.match /^\s*$/
  end

  def isYelling?(message)
    message.match /^[^a-z]+$/
  end

  def isQuestion?(message)
    message.match /\?$/
  end

  private :isSilence?, :isYelling?, :isQuestion?

end
