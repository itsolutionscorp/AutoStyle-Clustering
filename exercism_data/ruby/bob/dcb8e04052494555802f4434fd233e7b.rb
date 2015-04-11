class Bob
  def hey(statement)
    if blank?(statement)
      "Fine. Be that way!"
    elsif upcase?(statement)
      "Woah, chill out!"
    elsif question?(statement)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def blank?(statement)
    statement.nil? || statement.strip.length == 0
  end

  def upcase?(statement)
    statement == statement.upcase
  end

  def question?(statement)
    statement.end_with?("?")
  end

end
