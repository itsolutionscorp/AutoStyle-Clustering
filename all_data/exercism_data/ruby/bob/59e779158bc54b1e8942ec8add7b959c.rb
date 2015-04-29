class Bob

  def hey(string)
    return "Woah, chill out!" if yelled_at?(string)
    return "Sure." if asked_a_question?(string)
    return "Fine. Be that way!" if given_silent_treatment?(string)
    "Whatever."
  end

  def asked_a_question?(string)
    string.end_with?("?")
  end

  def yelled_at?(string)
    upcased_string = string.upcase
    string == upcased_string && upcased_string =~ /[A-Z]/
  end

  def given_silent_treatment?(string)
    string.strip.empty?
  end
end
