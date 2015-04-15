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
    has_no_lowercase_letters(str) && has_uppercase_letters(str)
  end

  def question?(str)
    str.end_with?("?")
  end

  def silence?(str)
    str.lstrip == ""
  end

  private

  def has_no_lowercase_letters(str)
    str == str.upcase
  end

  def has_uppercase_letters(str)
    str != str.downcase
  end

end
