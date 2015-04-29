class Bob
  def hey(statement)
    if statement.strip.empty?
      "Fine. Be that way!"
    elsif statement !~ /[a-z]+/ && statement =~ /[A-Z]+/
      "Woah, chill out!"
    elsif statement[-1,1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
