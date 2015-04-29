class Bob
  def hey(statement)
    return "Fine. Be that way." if silence?(statement)
    return "Woah, chill out!"   if yelling?(statement)
    return "Sure."              if question?(statement)
    "Whatever."
  end

  private

  def question?(statement)
    statement.end_with?("?")
  end

  def yelling?(statement)
    statement.to_s.upcase == statement.to_s
  end

  def silence?(statement)
    statement.nil? || statement == ""
  end
end
