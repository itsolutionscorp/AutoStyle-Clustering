class Bob
  
  def hey input

    # only whitespace:
    if input =~ /\A\s*\Z/
      "Fine. Be that way!"

    # has capital letters and does not have lowercase:
    elsif input =~ /[A-Z]/ && !(input =~ /[a-z]/)
      "Woah, chill out!"

    # ends in a question mark:
    elsif input =~ /\?\Z/
      "Sure."

    else
      "Whatever."

    end
  end

end
