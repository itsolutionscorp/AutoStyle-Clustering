class Bob
  def hey(statement)
    return "Fine. Be that way." if silence?(statement)
    return "Woah, chill out!" if shouting?(statement)
    return "Sure." if asking?(statement)
    "Whatever."
  end

  def shouting?(statement)
    statement == statement.upcase
  end

  def asking?(statement)
    statement.end_with?("?")
  end

  def silence?(statement)
    statement.nil? || statement.empty?
  end
end
