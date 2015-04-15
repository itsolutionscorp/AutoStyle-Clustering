class Bob
  def hey(user_input)
    return "Fine. Be that way!" if silent?(user_input)
    return "Woah, chill out!" if screaming?(user_input)
    return "Sure." if question?(user_input)
    return "Whatever."
  end

  def silent?(user_input)
    user_input.rstrip == ""
  end

  def screaming?(user_input)
    all_upcase?(user_input) && letters?(user_input)
  end

  def question?(user_input)
    user_input.rstrip.end_with?("?")
  end

  def all_upcase?(user_input)
    user_input == user_input.upcase
  end

  def letters?(user_input)
    user_input =~ /[a-zA-Z]/
  end

end
