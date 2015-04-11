class Bob
  def hey(input)
    return "Woah, chill out!" if yelling(input)
    return "Sure." if question(input)
    return "Fine. Be that way!" if silence(input)
    return "Whatever."
  end

private
  def yelling(input)
    input.upcase == input && input =~ /[a-zA-Z]/
  end

  def question(input)
    input.end_with?('?')
  end

  def silence(input)
    input.strip.empty?
  end
end
