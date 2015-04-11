class Bob
  def hey(statement)
    if silent?(statement)
      "Fine. Be that way!"
    elsif yelling?(statement)
      "Woah, chill out!"
    elsif questioning?(statement)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def silent?(statement)
    statement.to_s.strip.empty?
  end

  def yelling?(statement)
    statement == statement.upcase
  end

  def questioning?(statement)
    statement.end_with?("?")
  end

end
