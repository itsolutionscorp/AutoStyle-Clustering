class Bob
  def hey(str)
    case
    when str.strip.empty?
      "Fine. Be that way!"
    when str =~ /\A[^[[:lower:]]]*([[:upper:]]+)[^[[:lower:]]]*\z/
      "Woah, chill out!"
    when str =~ /\?\z/
      "Sure."
    else
      "Whatever."
    end
  end
end
