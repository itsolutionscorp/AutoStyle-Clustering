class Bob
  def hey(string)
    if silence? string
        return "Fine. Be that way!"
    elsif shouting? string
        return "Woah, chill out!"
    elsif question? string
        return "Sure."
    else
        return "Whatever."
    end
  end

  def question?(string)
    return string.end_with? "?"
  end

  def shouting?(string)
    (string.upcase == string) && ((string =~ /^[A-Za-z]/) || (string.end_with? "!"))
  end

  def silence?(string)
    string.strip == ''
  end
end
