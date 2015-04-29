class Bob
  def hey(s)
    case s
    when /^[^a-z]{2,}(\?|\!)$/, /^([A-Z]+\s*)+$/
      "Woah, chill out!"
    when /\?\z/
      "Sure."
    when /^\s+$/, ''
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
