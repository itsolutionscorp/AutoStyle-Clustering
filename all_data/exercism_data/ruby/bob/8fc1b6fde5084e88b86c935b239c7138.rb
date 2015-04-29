class Bob
  def hey(str)
    return "Woah, chill out!"   if shout?(str) 
    return "Sure."              if question?(str)
    return "Fine. Be that way!" if silence?(str)
    return "Whatever."
  end

  def shout?(str)
    has_no_lowercase_letters(str) && has_uppercase_letters(str)
  end

  def question?(str)
    str.end_with?("?")
  end

  def silence?(str)
    str.strip == ""
  end

  private

  def has_no_lowercase_letters(str)
    str == str.upcase
  end

  def has_uppercase_letters(str)
    str != str.downcase
  end

end
