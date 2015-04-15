class Bob
  def hey(statement)
    case statement.to_s
    when /\A\s?\B/ then "Fine. Be that way!"
    when statement.upcase then "Woah, chill out!"
    when /\?\Z/ then "Sure."
    else
      "Whatever."
    end
  end
end
