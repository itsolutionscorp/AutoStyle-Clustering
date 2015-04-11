class Bob
  def hey(text)
    return "Fine. Be that way!" if text.strip.empty?
    return "Woah, chill out!" if text =~ /[A-Z]/ && text =~ /\A[A-Z0-9 !\?,%^@#\*\$\(]+\z/
    return "Sure." if text =~ /\?\z/
    "Whatever."
  end
end
