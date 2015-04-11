class Bob
  def hey(str)
    if silent?(str)
      "Fine. Be that way!"
    elsif shouting?(str)
      "Woah, chill out!"
    elsif asking?(str)
      "Sure."
    else
      "Whatever."
    end
  end

  def silent?(str)
    str.strip.size == 0
  end

  def shouting?(str)
    str == str.upcase
  end

  def asking?(str)
    str.end_with?("?")
  end
end
