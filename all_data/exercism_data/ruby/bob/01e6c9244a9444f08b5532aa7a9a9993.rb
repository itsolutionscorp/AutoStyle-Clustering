class Bob
  def hey(statement)
    @statement = statement

    case
    when silent?;     "Fine. Be that way!"
    when shouting?;   "Woah, chill out!"
    when question?;   "Sure."
    else;             "Whatever."
    end
  end

  private

  def silent?
    @statement.gsub(/\s+/, "") == ""
  end

  def shouting?
    @statement.upcase == @statement
  end

  def question?
    @statement.end_with?("?")
  end
end
