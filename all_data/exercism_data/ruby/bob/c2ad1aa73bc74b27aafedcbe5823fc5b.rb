class String

  def is_shout?
    self == self.upcase && self != self.downcase
  end

  def is_query?
    self.strip.end_with?("?")
  end

  def is_quiet?
    self.strip.empty?    
  end
    
end


class Bob

  def hey(input)
    case 
    when input.is_shout?
      "Woah, chill out!"
    when input.is_query?
      "Sure."
    when input.is_quiet?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

end
