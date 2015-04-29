class Bob

  def hey(statement)

    if statement.strip == ""
      "Fine. Be that way!"

    elsif  (statement == statement.upcase) && !(statement.upcase == statement.downcase)
      #statement =~/[a-zA-Z]/
      "Whoa, chill out!"

    elsif  statement.end_with?('?')
      "Sure."

    else
      "Whatever."
    end

  end

end
