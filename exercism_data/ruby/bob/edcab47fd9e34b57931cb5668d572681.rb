class Bob
  def hey(text)
    if yelling?(text)
      "Woah, chill out!"
    elsif question?(text)
      "Sure."
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
    str == str.upcase && str.match(/[a-zA-Z]+/)
  end

  def silence?(str)
    str == str[/\s*/]
  end
end
