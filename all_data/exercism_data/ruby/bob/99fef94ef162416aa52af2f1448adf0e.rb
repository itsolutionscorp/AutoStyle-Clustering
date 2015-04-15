class Bob
  def hey(str)
    return "Fine. Be that way." if empty?(str)
    return "Woah, chill out!"   if yelling?(str)
    return "Sure."              if is_a_question?(str)
    "Whatever."
  end

  private

  def is_a_question?(str)
    str[-1] == "?"
  end

  def yelling?(str)
    str.to_s.upcase == str.to_s
  end

  def empty?(str)
    str.nil? || str == ""
  end
end
