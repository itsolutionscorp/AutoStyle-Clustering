class Bob

  def hey(statement)
    if silence?(statement)
      "Fine. Be that way."
    elsif shouting?(statement)
      "Woah, chill out!"
    elsif question?(statement)
      "Sure."
    else
      "Whatever."
    end
  end

  def silence?(statement)
    statement.empty?
  end

  def shouting?(statement)
    statement == statement.upcase
  end

  def question?(statement)
    statement.end_with?("?")
  end
end
