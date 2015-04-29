class Bob
  def hey(statement)
    if is_silence?(statement)
      "Fine. Be that way!"
    elsif is_all_caps?(statement)
      "Woah, chill out!"
    elsif is_question?(statement)
      "Sure."
    else
      "Whatever."
    end
  end

  def is_silence?(statement)
    statement.strip == ""
  end

  def is_all_caps?(statement)
    statement.upcase == statement
  end

  def is_question?(statement)
    statement_last_character = statement.reverse[0]
    statement_last_character == "?"
  end

end
