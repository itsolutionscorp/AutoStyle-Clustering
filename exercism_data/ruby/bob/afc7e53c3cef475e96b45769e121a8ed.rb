class Bob
  def hey(statement)
    statement = statement.to_s.strip

    if statement.empty?
      "Fine. Be that way!"
    elsif statement == statement.upcase
      "Woah, chill out!"
    elsif statement.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end
end
