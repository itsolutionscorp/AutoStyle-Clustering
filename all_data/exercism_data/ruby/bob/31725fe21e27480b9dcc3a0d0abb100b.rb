class Bob
  def yelling?
    @text =~ /[A-Z]/ && @text !~ /[a-z]/
  end

  def asking?
    @text =~ /\?\s*\z/
  end

  def silence?
    @text =~ /\A\s*\z/
  end

  def hey(text)
    @text = text
    return "Whoa, chill out!" if yelling?
    return "Sure." if asking?
    return "Fine. Be that way!" if silence?
    "Whatever."
  end
end
