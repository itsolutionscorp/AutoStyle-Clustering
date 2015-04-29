class Bob

  def hey words

    shouting = /\A[^a-z]*[[:upper:]]+[^a-z]*\z/
    question = /\?\z/
    silence =  /\A\s*\z/
    
    case words
    when shouting
      return  "Woah, chill out!"
    when question
      return "Sure."
    when silence
      return "Fine. Be that way!"
    else
      "Whatever."
    end
  end

end
