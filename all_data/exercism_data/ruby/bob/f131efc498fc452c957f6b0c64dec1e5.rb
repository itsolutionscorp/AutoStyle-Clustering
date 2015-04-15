class Bob
  def hey(statement)
    if silence?(statement)
      "Fine. Be that way!"
    elsif shouting?(statement)
      "Woah, chill out!"
    elsif question?(statement)
      "Sure."
    else
      "Whatever."
    end
  end

  private
  def silence?(statement)
    statement.to_s.strip.empty?
  end

  def shouting?(statement)
    statement == statement.upcase
  end

  def question?(statement)
    statement.end_with?("?")
  end
end
