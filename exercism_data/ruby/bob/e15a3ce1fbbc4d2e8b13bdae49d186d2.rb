class Bob
  def hey(statement)
    silent(statement)   || shouting(statement) ||
    question(statement) || "Whatever."
  end

  private

  def silent(statement)
    "Fine. Be that way!" if statement.gsub(/\s+/, "") == ""
  end

  def shouting(statement)
    'Woah, chill out!' if statement.upcase == statement
  end

  def question(statement)
    "Sure." if statement[-1] == "?"
  end
end
