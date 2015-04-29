class Bob
  def hey(str)
    return "Woah, chill out!"   if shout?(str)
    return "Sure."              if question?(str)
    return "Fine. Be that way!" if quiet?(str)

    "Whatever."
  end

  private
  def question?(str)
    str[-1] == "?"
  end

  def shout?(str)
    str.upcase == str and contains_words?(str)
  end

  def quiet?(str)
    str.strip.empty?
  end

  def contains_words?(str)
    ! str.scan(/[a-zA-Z]/).empty?
  end
end
