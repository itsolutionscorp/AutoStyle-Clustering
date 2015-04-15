class Bob
  # A yell is an input that includes at least some uppercase letters, but no
  # lowercase letters (if it's just numbers/symbols/punctuation, it's not 
  # shouting, according to the test suite).
  YELL = /\A[^a-z]*[A-Z]+[^a-z]*\Z/

  # Questions are text inputs that end in a question mark. We also allow 
  # trailing whitespace to follow the question mark.
  QUESTION = /\?\s*\Z/

  # A nothing is an input that doesn't include any text (just whitespace).
  NOTHING = /\A\s*\Z/

  def hey(input)
    case input
    when YELL
      "Woah, chill out!"
    when QUESTION
      "Sure."
    when NOTHING
      "Fine. Be that way!"
    else 
      "Whatever."
    end
  end
end
