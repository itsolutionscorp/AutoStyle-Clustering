class Bob

  def hey(input)
    case
    when input.strip.empty?
      return "Fine. Be that way!"
    when input.upcase == input && input.downcase != input
      return "Woah, chill out!"
    when input.strip.end_with?('?') 
      return "Sure."
    else
      return "Whatever."
    end
  end
end
