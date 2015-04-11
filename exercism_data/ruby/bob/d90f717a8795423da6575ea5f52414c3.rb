class Bob
  def hey(text)
    if question?(text)
      "Sure."
    elsif yelling?(text)
      "Woah, chill out!"
    elsif silence?(text)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  def question?(str)
    str.end_with?('?')
  end

  def yelling?(str)
    str == str.upcase
  end

  def silence?(str)
    str == str[/\s*/]
  end
end
