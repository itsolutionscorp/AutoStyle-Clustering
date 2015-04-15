class Bob
  def hey(line)
    if !(line =~ /\w/)
      "Fine. Be that way!"
    elsif line =~ /[A-Z]/ && !(line =~ /[a-z]/)
      "Woah, chill out!"
    elsif line =~ /\?\Z/
      "Sure."
    else
      "Whatever."
    end
  end
end
