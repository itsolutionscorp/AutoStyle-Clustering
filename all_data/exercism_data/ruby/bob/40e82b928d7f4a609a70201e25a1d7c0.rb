class Bob
  def hey(str)
    case str
    when /\A\s*\Z/
      "Fine. Be that way!"
    when /\A[^:lower:]*[[:upper:]][^:lower:]*\Z/
      "Woah, chill out!"
    when /\?\Z/
      "Sure."
    else
      "Whatever."
    end
  end
end
