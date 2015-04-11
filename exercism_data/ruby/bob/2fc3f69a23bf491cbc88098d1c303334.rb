class Bob
  
  def hey(input)
    if input !~ /\S/    # check if it includes any non white space, this catches empty strings as well
      'Fine. Be that way!'
    elsif input == input.upcase && input =~ /[A-Z]/  # if input is the same upcased or not and it it includes at least one character A..Z
      "Woah, chill out!"
    elsif input[-1] == "?" # only if the last character is a question mark
      "Sure."
    else  # the rest of the situations
      "Whatever."
    end
  end

end
