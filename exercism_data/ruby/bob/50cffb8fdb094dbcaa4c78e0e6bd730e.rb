class Bob
  def hey(text)
    text = text.to_s

    return "Sure."              if asking?(text)
    return 'Fine. Be that way.' if silence?(text)
    return "Woah, chill out!"   if yelling?(text)
    return "Whatever."
  end

  private

  def yelling?(text)
    text.upcase == text
  end

  def asking?(text)
    text.end_with?("?")
  end

  def silence?(text)
    text.empty?
  end
end
