class Bob
  def hey(phrase)
    return "Fine. Be that way!" if phrase.strip.empty?
    return "Whoa, chill out!" if phrase =~ /[A-Z]/ && phrase !~ /[a-z]/
    return "Sure." if phrase.end_with?("?")
    "Whatever."
  end
end
