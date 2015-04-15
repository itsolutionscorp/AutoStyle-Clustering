class Bob

  def hey input

    input = input.to_s

    if passive_aggressive?(input) then "Fine. Be that way!"
    elsif yelling?(input) then  "Woah, chill out!"
    elsif questioning?(input) then "Sure."
    else "Whatever."
    end

  end

  private

  def passive_aggressive? input
    input =~ /^\s*$/
  end

  def yelling? input
    input == input.upcase
  end

  def questioning? input
    input =~ /.*\?$/
  end

end
