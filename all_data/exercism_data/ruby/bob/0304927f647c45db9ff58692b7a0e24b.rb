class Bob
  def hey(statement)
    if statement == nil or statement.strip.empty?
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
