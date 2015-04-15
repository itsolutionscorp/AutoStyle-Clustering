class Bob

  def hey(statement)
    if statement == ""
      "Fine. Be that way."
    elsif statement == statement.upcase
      "Woah, chill out!"
    elsif statement.split("").last == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
