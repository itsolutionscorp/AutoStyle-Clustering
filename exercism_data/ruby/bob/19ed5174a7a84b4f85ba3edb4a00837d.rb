class Bob
  def hey(statement)
    statement = statement.to_s.strip

    if silent(statement)
      "Fine. Be that way!"
    elsif yelling(statement)
      "Woah, chill out!"
    elsif questioning(statement)
      "Sure."
    else
      "Whatever."
    end
  end

private
  def silent(statement)
    statement.empty?
  end

  def yelling(statement)
    statement == statement.upcase
  end

  def questioning(statement)
    statement.end_with?("?")
  end
end
