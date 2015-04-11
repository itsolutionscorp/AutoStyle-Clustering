class Bob
  SILENCE = /\A\s*\Z/
  SHOUT = /\A[^a-z]+\Z/
  QUESTION = /\?\Z/

  def hey message
    case message
    when SILENCE
      "Fine. Be that way!"
    when SHOUT
      "Woah, chill out!"
    when QUESTION
      "Sure."
    else
      "Whatever."
    end
  end
end
