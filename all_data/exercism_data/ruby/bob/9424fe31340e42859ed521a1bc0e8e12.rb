class Bob

  def hey words

    shouting = /\A[^a-z]*[[:upper:]]+[^a-z]*\z/
    question = /\?\z/
    silence =  /\A\s*\z/
    
    case words
    when shouting
      "Woah, chill out!"
    when question
      "Sure."
    when silence
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

end
