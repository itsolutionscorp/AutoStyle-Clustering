class Bob
  def hey(user_input)
    return "Fine. Be that way!" if is_silent?(user_input)
    return "Woah, chill out!" if is_screaming?(user_input)
    return "Sure." if is_question?(user_input)
    return "Whatever."
  end

  def is_silent?(user_input)
    user_input.rstrip == ""
  end

  def is_screaming?(user_input)
    is_all_upcase?(user_input) && has_letters?(user_input)
  end

  def is_question?(user_input)
    user_input.rstrip.end_with?("?")
  end

  def is_all_upcase?(user_input)
    user_input == user_input.upcase
  end

  def has_letters?(user_input)
    user_input =~ /[a-zA-Z]/
  end

end
