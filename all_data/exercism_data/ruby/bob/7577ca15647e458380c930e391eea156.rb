class Bob
  def hey(what)
    return "Whatever." if what.match(/\n/)
    return "Fine. Be that way!" if what.strip.empty?
    return "Woah, chill out!" if yelling(what.strip)
    return "Sure." if question(what.strip)
    return "Whatever."
  end

  def yelling(string)
    # Must have letters that are all caps
    return unless string.match(/[a-zA-Z]/)
    string.upcase == string
  end

  def question(string)
    # Ends with a question mark
    string =~ /\?$/
  end
end
