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
    if (string.count("0-9") == 0) && (string.upcase == string)
      return true
    elsif ((string.count("0-9") > 0) && (string.end_with? "!"))
      return true
    else
      return false
    end
  end

  def silence?(string)
    if string == '' || string.strip! == ''
      return true
    end
  end
end
