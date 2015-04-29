class Bob
  def hey(statement)
    return "Fine. Be that way." if statement.nil? || statement == ""
    what_was_said = Statement.new(statement)
    return "Woah, chill out!" if what_was_said.shouting?
    return "Sure." if what_was_said.asking?
    "Whatever."
  end
end

class Statement < String
  def shouting?
    self == self.upcase
  end

  def asking?
    self.end_with?("?")
  end
end
