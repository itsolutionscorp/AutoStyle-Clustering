class Bob

  def hey(input)
    return "Woah, chill out!" if yelling?(input)
    return "Sure." if asking?(input)
    return "Fine. Be that way!" if saying_nothing?(input)
    return "Whatever."
  end

  private

  def yelling?(input)
    input = strip(input)
    input.length > 0 && input.upcase == input
  end

  def asking?(input)
    input.to_s.end_with? '?'
  end

  def saying_nothing?(input)
    strip(input).empty?
  end

  def strip(input)
    input.to_s.gsub /[^a-zA-Z]/, ""
  end

end
