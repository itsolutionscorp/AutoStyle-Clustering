class Bob
  def hey(message)
    if message =~ /\A\s*\Z/
      "Fine. Be that way!"
    elsif message =~ /\A[^a-z]+\Z/
      "Woah, chill out!"
    elsif message =~ /\?\Z/
      "Sure."
    else
      "Whatever."
    end
  end
end
