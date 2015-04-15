class Bob
  def hey(say)
    return "Fine. Be that way!" if you_are_silent(say)
    return "Woah, chill out!"   if you_are_shouting(say)
    return "Sure."              if you_are_asking_a_question(say)
    "Whatever."
  end

  def you_are_silent(string)
    string.strip.empty?
  end

  def you_are_shouting(string)
    string == string.upcase
  end

  def you_are_asking_a_question(string)
    string[-1] == "?"
  end
end
