class Bob
  def hey(input)
    return "Woah, chill out!" if yelling?(input)
    return "Sure." if questioning?(input)
    return "Fine. Be that way!" if wasting_time?(input)
    "Whatever."
  end

  private

  def yelling?(input)
    input.upcase == input && input.match(/[a-zA-Z]+/)
  end

  def questioning?(input)
    input.end_with?("?")
  end

  def wasting_time?(input)
    input.strip == ""
  end
end
