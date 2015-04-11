class Bob

  def hey(statement)
    if statement == statement.upcase && statement != statement.downcase
      "Whoa, chill out!"
    elsif statement.end_with?("?")
      "Sure."
    elsif statement.strip.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

end
