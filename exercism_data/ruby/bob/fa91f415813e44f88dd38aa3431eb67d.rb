class Bob
  def hey(statement)
    if statement.upcase == statement && statement =~ /[A-Z]/
       "Whoa, chill out!"
     elsif statement.end_with?("?")
       "Sure."
     elsif statement.strip.empty? #nothing but whitespace
        "Fine. Be that way!"
     else
        "Whatever."
    end
  end
end
