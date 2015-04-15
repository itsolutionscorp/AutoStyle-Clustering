class Bob
  def hey(statement)
    @statement = statement

    if is_silence?
      "Fine. Be that way!"
    elsif is_all_caps?
      "Woah, chill out!"
    elsif is_question?
      "Sure."
    else
      "Whatever."
    end
  end

  def is_silence?
    @statement.strip == ""
  end

  def is_all_caps?
    @statement.upcase == @statement
  end

  def is_question?
    statement_last_character = @statement.reverse[0]
    statement_last_character == "?"
  end

end
