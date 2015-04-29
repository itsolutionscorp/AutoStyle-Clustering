class Bob

  def hey string
    return "Fine. Be that way!" if string.strip.empty?
    return "Woah, chill out!" if string == string.upcase && string =~ /[a-zA-Z]/
    return "Sure." if string.end_with? ("?")
    return "Whatever."
  end

end
