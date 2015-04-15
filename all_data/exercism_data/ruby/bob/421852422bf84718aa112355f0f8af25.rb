class Bob
  def hey(whatever)
    case whatever
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
  
  def shouting
    /[A-Z][A-Z]+\s+[A-Z][A-Z]+|GO!\z/
  end

  def question
    /\?\z/  
  end

  def silence
    /\A\s*\z/
  end
end
