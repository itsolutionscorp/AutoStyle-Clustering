class Bob
  def hey ( input )
    output = "Whatever."
    if input.strip == ""
      output = "Fine. Be that way!"
    elsif input == input.upcase and /[a-zA-Z]/ =~ input
      output = "Woah, chill out!"
    elsif /\?\z/m =~ input
      output = "Sure."
    end
    output
  end
end
