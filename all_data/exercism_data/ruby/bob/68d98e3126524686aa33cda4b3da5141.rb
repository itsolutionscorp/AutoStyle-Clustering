class Bob
  def hey(statement)
    if !statement || statement.empty?
      return "Fine. Be that way."
    elsif statement == statement.upcase
      return "Woah, chill out!"
    elsif statement.end_with?("?")
      return "Sure."
    else
      return "Whatever."
    end
  end
end
