class Bob
  def hey(str)
    if shout?(str)
      "Woah, chill out!"
    elsif question?(str)
      "Sure."
    elsif silence?(str)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  def shout?(str)
    str.upcase == str && str.downcase != str
  end

  def question?(str)
    str.end_with?("?")
  end

  def silence?(str)
    str.strip == ""
  end
end
