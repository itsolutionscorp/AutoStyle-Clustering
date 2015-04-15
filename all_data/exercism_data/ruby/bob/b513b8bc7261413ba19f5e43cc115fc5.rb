class Bob
  def hey(words)
    if words.strip == ""
      "Fine. Be that way!"
    elsif words =~ /\A[0-9A-Z\W]+\Z/
      "Woah, chill out!"
    elsif words =~ /\?\Z/
      "Sure."
    else
      "Whatever."
    end
  end
end
