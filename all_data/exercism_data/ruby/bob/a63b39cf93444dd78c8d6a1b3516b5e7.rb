class Bob
  attr_reader :input

  def hey(input)
    @input = input
    case 
    when silence?
      "Fine. Be that way!"
    when shouting?
      "Woah, chill out!"
    when questioning?
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def silence?
    input.strip == ""
  end

  def shouting?
    input =~ /[a-zA-Z]/ && input.upcase == input
  end

  def questioning?
    input.end_with?("?")
  end
end
