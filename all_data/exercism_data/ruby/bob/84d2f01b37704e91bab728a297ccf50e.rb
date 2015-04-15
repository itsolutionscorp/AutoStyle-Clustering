class Bob

  def hey input


    input = input.to_s
    case true
    when is_passive_aggressive(input)
      "Fine. Be that way!"
    when is_yelling(input)
      "Woah, chill out!"
    when is_question(input)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def is_passive_aggressive input
    input =~ /^\s*$/ ? true : false
  end

  def is_yelling input
    input == input.upcase
  end

  def is_question input
    input =~ /.*\?$/ ? true: false
  end

end
