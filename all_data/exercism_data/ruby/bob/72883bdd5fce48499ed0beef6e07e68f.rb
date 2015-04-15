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

  private
  def silent?(str)
    str.strip.empty?
  end

  def shouting?(str)
    str == str.upcase
  end

  def asking?(str)
    str.end_with?("?")
  end
end
