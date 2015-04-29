class Bob
  def hey(statement)
    statement = statement.to_s.strip

    if check_empty(statement)
      "Fine. Be that way!"
    elsif check_upcase(statement)
      "Woah, chill out!"
    elsif check_question(statement)
      "Sure."
    else
      "Whatever."
    end
  end

private
  def check_empty(statement)
    statement.empty?
  end

  def check_upcase(statement)
    statement == statement.upcase
  end

  def check_question(statement)
    statement.end_with?("?")
  end
end
