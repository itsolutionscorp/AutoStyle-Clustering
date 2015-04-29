class Bob
  def hey(accost)
    case accost
    # if we address him without actually saying anything
    when %r{\A[^\w]*\z}
      "Fine. Be that way!" 
    # if we yell at him (ALL CAPS)
    when %r{\A[^[:lower:]]+\z} 
      "Woah, chill out!"   
    # if we ask him a question
    when %r{\A.*\?\z}
      "Sure."    
    # anything else
    else "Whatever."
    end
  end
end
