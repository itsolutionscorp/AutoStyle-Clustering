class Bob
  def hey(statement)
    case statement
    when /^[^a-z]*[A-Z][^a-z]*$/
     "Woah, chill out!"
    when /\?$/
      "Sure."
    when /^\s*$/
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
