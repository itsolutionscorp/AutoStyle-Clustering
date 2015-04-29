class Bob
  def hey(statement)
    if silence?(statement)
      "Fine. Be that way."
    elsif yelling?(statement)
      "Woah, chill out!"
    elsif question?(statement)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def question?(statement)
    statement.end_with?("?")
  end

  def yelling?(statement)
    statement.upcase == statement
  end

  def silence?(statement)
    statement.to_s == ""
  end
end
