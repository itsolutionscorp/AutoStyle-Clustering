class Bob
  def hey(statement)
    return "Fine. Be that way!" if silence?(statement)
    return "Woah, chill out!"   if shouting?(statement)
    return "Sure."              if question?(statement)
    return "Whatever."
  end

  private

  def silence?(statement)
    statement.strip.empty?
  end

  def question?(statement)
    statement.end_with?("?")
  end

  def shouting?(statement)
    statement.upcase == statement
  end
end
