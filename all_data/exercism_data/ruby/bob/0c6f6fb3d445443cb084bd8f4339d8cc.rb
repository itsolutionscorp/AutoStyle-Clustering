class Bob
  def initialize
  end
  
  def hey(input)
    case 
    when input == nil || input.empty?
      "Fine. Be that way."
    when input == input.upcase
      "Woah, chill out!"
    when input.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end
end