class Bob

  def hey(statement)
    (is_silence? statement and "Fine. Be that way!") or
    (is_yelling? statement and "Woah, chill out!") or
    (is_question? statement and "Sure.") or
    "Whatever."
  end

  def is_silence?(statement)
    if statement
      statement.strip.empty?
    else
      true
    end
  end

  def is_yelling?(statement)
    statement.upcase == statement
  end

  def is_question?(statement)
    statement[-1] == "?"
  end

end
