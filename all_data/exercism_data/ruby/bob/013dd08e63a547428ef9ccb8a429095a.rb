class Bob

  def hey input

    input = input.to_s
    case true
    when passive_aggressive?(input) then "Fine. Be that way!"
    when yelling?(input) then  "Woah, chill out!"
    when questioning?(input) then "Sure."
    else
      "Whatever."
    end
  end

  private

  def passive_aggressive? input
    input =~ /^\s*$/ ? true : false
  end

  def yelling? input
    input == input.upcase
  end

  def questioning? input
    input =~ /.*\?$/ ? true: false
  end

end
