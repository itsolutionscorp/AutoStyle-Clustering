class Bob
  def hey(input)
    if empty(input)
      "Fine. Be that way!"
    elsif chill(input)
      "Woah, chill out!"
    elsif sure(input)
      "Sure."
    else
      "Whatever."
    end
  end

  private
  def chill(input)
    input.upcase == input && input =~ /[A-Z]/
  end

  def sure(input)
    input.end_with?("?")
  end

  def empty(input)
    input.strip == ""
  end
end
