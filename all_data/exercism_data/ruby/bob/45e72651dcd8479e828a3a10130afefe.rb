class Bob
  def hey(input)
    case input
    when /\A[^a-z]*[A-Z]+[^a-z]*\Z/   # no lowercase letters, some uppercase letters
      "Woah, chill out!"
    when /\?\s*\Z/                    # ends in a question mark (and possibly whitespace)
      "Sure."
    when /\A\s*\Z/                    # only whitespace
      "Fine. Be that way!"
    else 
      "Whatever."
    end
  end
end
