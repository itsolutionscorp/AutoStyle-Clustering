class Bob
  def hey(text)
    @text = text
    return "Whoa, chill out!" if yelling?
    return "Sure." if asking?
    return "Fine. Be that way!" if silence?
    "Whatever."
  end

  private

  def yelling?
    # Test for all caps text
    @text =~ /[A-Z]/ && @text !~ /[a-z]/
  end

  def asking?
    # Test for questions (end with question mark)
    @text =~ /\?\s*\z/
  end

  def silence?
    # Test for all spaces
    @text =~ /\A\s*\z/
  end

end
