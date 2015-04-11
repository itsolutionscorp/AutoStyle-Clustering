class Bob
  def hey(statement)
    case statement
    when '', nil then "Fine. Be that way!"
    when statement.upcase then "Woah, chill out!"
    when /\?$/ then "Sure."
    else
      "Whatever."
    end
  end
end
