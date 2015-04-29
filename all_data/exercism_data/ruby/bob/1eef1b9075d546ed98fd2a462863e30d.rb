class Bob
  def hey(input)
    if input =~ /[a-z]/ and input[-1] != "?"
      "Whatever."
    elsif input =~ /[a-z]/
      "Sure."
    elsif input =~ /[A-Z]/
      "Woah, chill out!"
    else
      "Fine. Be that way."
    end
  end
end
