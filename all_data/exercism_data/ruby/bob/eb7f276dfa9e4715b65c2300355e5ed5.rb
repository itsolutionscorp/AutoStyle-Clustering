class Bob
  def hey(statement)
    if talking?(statement)
      return "Fine. Be that way."
    elsif shouting?(statement)
      return "Woah, chill out!"
    elsif question?(statement)
      return "Sure."
    else
      return "Whatever."
    end
  end

  private

  def talking?(statement)
    statement.to_s.empty?
  end

  def shouting?(statement)
    statement == statement.upcase
  end

  def question?(statement)
    statement.end_with?("?")
  end
end
