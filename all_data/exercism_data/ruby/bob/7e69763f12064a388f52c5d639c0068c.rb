class Bob

  def hey words
    
    case words
    when /\A[^a-z]*[[:upper:]]+[^a-z]*\z/
      return  "Woah, chill out!"
    when /\?\z/
      return "Sure."
    when   /\A\s*\z/
      return "Fine. Be that way!"
    else
      "Whatever."
    end
  end

end
