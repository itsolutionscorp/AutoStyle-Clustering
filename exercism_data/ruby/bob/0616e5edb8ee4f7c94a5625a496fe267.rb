class Bob
  def hey(statement)
    if statement.match(/\A[A-Z0-9]+[^a-z]+[A-Z]+[^a-z]*[!?]?\z/)
      "Woah, chill out!"
    elsif statement.match(/\?\z/)
      "Sure."
    elsif statement.match(/\A\s*\z/)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
