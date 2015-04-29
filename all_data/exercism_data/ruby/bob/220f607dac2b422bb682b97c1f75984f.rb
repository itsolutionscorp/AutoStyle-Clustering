class Bob

  def hey(message)
    case message
    when ALL_BLANKS
      "Fine. Be that way!"
    when ALL_CAPITAL_LETTERS
      "Woah, chill out!"
    when ENDS_IN_A_QUESTION_MARK
      "Sure."
    else
      "Whatever."
    end
  end

  ALL_BLANKS              = /\A *\z/
  ALL_CAPITAL_LETTERS     = /\A[^a-z]+\z/
  ENDS_IN_A_QUESTION_MARK = /\?\z/
end
