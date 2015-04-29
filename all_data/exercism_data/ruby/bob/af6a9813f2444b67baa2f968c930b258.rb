class Bob

  def hey(input)
    return "Woah, chill out!" if all_caps?(input)
    return "Sure." if asking?(input)
    return "Fine. Be that way!" if strip(input).empty?
    return "Whatever."
  end

  private

  def all_caps?(input)
    input = strip(input)
    input.length > 0 && input.upcase == input
  end

  def asking?(input)
    input.to_s.end_with? '?'
  end

  def strip(input)
    input.to_s.gsub /[^a-zA-Z]/, ""
  end

end
