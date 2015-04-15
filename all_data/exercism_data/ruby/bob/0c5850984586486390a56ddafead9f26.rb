class Bob
  def hey(string)
    if silence? string
        "Fine. Be that way!"
    elsif shouting? string
        "Woah, chill out!"
    elsif question? string
        "Sure."
    else
        "Whatever."
    end
  end

  def question?(string)
    string.end_with? "?"
  end

  def shouting?(string)
    (string.upcase == string) && ((string =~ /^[a-z]+/i) || (string.end_with? "!"))
  end

  def silence?(string)
    string.strip == ''
  end
end
