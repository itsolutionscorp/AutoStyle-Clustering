class Bob
  SHOUTING = /\A[^a-z]*[A-Z][^a-z]*\z/
  QUESTION = /\?\z/
  SILENCE  = /\A\s*\z/

  def hey(message)
    case message
    when SHOUTING
      "Woah, chill out!"
    when QUESTION
      "Sure."
    when SILENCE
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
