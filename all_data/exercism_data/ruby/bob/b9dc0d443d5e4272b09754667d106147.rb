class Bob
  def hey(text)
    return "Fine. Be that way!" if silence? text
    return "Woah, chill out!" if yelling? text
    return "Sure." if question? text

    "Whatever."
  end

  private

  def silence?(text)
    text.strip.empty?
  end

  def yelling?(text)
    text =~ /[a-z]/i and text == text.upcase
  end

  def question?(text)
    text.end_with? "?"
  end
end
