class Bob
  def hey(parental_statement)
    case 
    when silent_treatment_as(parental_statement)
      "Fine. Be that way!"
    when shouted_at_in(parental_statement)
      "Woah, chill out!"
    when questioned_by(parental_statement)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def silent_treatment_as(statement)
    statement.strip == '' ? true : false
  end

  def shouted_at_in(statement)
    statement == statement.upcase ? true : false
  end

  def questioned_by(statement)
    statement.chomp!('?') ? true : false
  end
end
