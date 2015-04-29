# Iteration 1 had bob.rb, iteration 2 had responder.rb; collapsing
# them together for convenience here. Iteration 3 is the final one.
module Responder
  RESPONSE_TO_SILENCE         = "Fine. Be that way!"
  RESPONSE_TO_YELLING         = "Woah, chill out!"
  RESPONSE_TO_QUESTIONING     = "Sure."
  RESPONSE_TO_EVERYTHING_ELSE = "Whatever."

  SILENT_PHRASE_STRIPPED_LENGTH = 0
  QUESTIONING_TOKEN             = "?"

  def respond_to(phrase)
    phrase = stringify!(phrase)
    return RESPONSE_TO_SILENCE     if given_silence?(phrase)
    return RESPONSE_TO_YELLING     if given_yelling?(phrase)
    return RESPONSE_TO_QUESTIONING if given_questioning?(phrase)
    return RESPONSE_TO_EVERYTHING_ELSE
  end

  def stringify!(phrase)
    phrase.to_s
  end

  def given_silence?(phrase)
    phrase.strip.length == SILENT_PHRASE_STRIPPED_LENGTH
  end

  def given_yelling?(phrase)
    phrase.upcase == phrase
  end

  def given_questioning?(phrase)
    phrase.end_with?(QUESTIONING_TOKEN)
  end
end

class Bob
  include Responder

  def hey(phrase)
    respond_to(phrase)
  end
end
