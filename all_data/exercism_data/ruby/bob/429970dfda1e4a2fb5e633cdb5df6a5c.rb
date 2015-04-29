class Bob
  def hey(parental_statement)
    if silent_treatment? parental_statement
      "Fine. Be that way!"
    elsif shouting? parental_statement
      "Woah, chill out!"
    elsif question? parental_statement
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def silent_treatment?(statement)
    statement.strip == ''
  end

  def shouting?(statement)
    statement == statement.upcase
  end

  def question?(statement)
    statement.chomp!('?')
  end
end
