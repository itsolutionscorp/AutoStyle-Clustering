module Responder
  RESPONSE_TO_SILENCE         = "Fine. Be that way!"
  RESPONSE_TO_YELLING         = "Woah, chill out!"
  RESPONSE_TO_QUESTIONING     = "Sure."
  RESPONSE_TO_EVERYTHING_ELSE = "Whatever."

  def reply_to(phrase)
    return RESPONSE_TO_SILENCE     if given_silence?(phrase)
    return RESPONSE_TO_YELLING     if given_yelling?(phrase)
    return RESPONSE_TO_QUESTIONING if given_questioning?(phrase)
    return RESPONSE_TO_EVERYTHING_ELSE
  end

  def given_silence?(phrase)
    phrase.strip.empty?
  end

  def given_yelling?(phrase)
    phrase.upcase == phrase
  end

  def given_questioning?(phrase)
    phrase.end_with?("?")
  end
end

class Bob
  include Responder

  def hey(phrase)
    reply_to(phrase.to_s)
  end
end
