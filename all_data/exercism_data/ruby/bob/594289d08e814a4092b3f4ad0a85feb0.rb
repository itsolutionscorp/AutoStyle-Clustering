class Bob
  QUESTION = /\?\z/
  YELL = /\A[^a-z]*[A-Z]+[^a-z]*\z/
  SILENCE = /\A *\z/

  def hey(arg)
    case arg
    when YELL
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
