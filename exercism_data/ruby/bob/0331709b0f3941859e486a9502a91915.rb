class Bob

  def hey(input)
    case 
      when input.strip.length < 1
      "Fine. Be that way!"
      when input == input.upcase && input != input.downcase
      'Woah, chill out!'
      when input[-1] == '?'             
      "Sure."
      else
      "Whatever."
      end
  end

end
