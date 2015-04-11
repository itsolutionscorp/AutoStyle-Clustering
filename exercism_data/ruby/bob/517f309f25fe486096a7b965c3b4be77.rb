class Bob
  def hey(text)
    return "Whoa, chill out!" if text =~ /[A-Z]/ && text !~ /[a-z]/
    return "Sure." if text =~ /\?\s*\z/
    return "Fine. Be that way!" if text =~ /\A\s*\z/
    "Whatever."
  end
end
