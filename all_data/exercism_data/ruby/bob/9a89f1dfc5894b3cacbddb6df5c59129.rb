class Bob

  def hey(input)
    if input =~ /[a-z]+\?/ # Any input ending with a question mark
        "Sure."
      elsif input =~ /[A-Z]{2,}\?*/   # string is in all caps
        "Woah, chill out!"
      elsif input == "" #Fine. Be that way!
        "Fine, be that way."
      else
        "Whatever."
    end
  end
end
