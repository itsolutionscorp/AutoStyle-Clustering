class Bob
  def hey(text)
    if text =~ /(\A[[:blank:]]*\Z)/
      "Fine. Be that way!"
    elsif text == text.upcase
      "Woah, chill out!"
    elsif text =~ /[[:word:]]*\?\Z/
      "Sure."
    else
      "Whatever."
    end
  end
end
