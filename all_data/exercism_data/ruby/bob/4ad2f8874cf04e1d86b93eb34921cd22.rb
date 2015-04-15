class Bob
  def hey(statement)
    if statement == statement.upcase && statement.match(/[a-zA-z]/)
      "Whoa, chill out!"
    elsif statement.end_with?("?")
      "Sure."
    elsif statement.end_with?("!") || statement.match(/[0-9]/) || statement.include?("?")
      "Whatever."
    elsif statement.strip == ''
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
