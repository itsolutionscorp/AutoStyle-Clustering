module Lackadaisical
  def response_to_question
    "Sure."
  end

  def response_to_yell
    "Woah, chill out!"
  end

  def response_to_silence
    "Fine. Be that way!"
  end

  def response
    "Whatever."
  end
end

class Bob
  include Lackadaisical

  def hey statement
    return response_to_silence if not statement or statement.strip.empty?
    return response_to_yell if statement.scan(/[a-z]/).empty? and not statement.scan(/[A-Z]/).empty?
    return response_to_question if statement.end_with? '?'

    response
  end
end
