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
    /
      [A-Z][A-Z]+       # An upper-case word (two or more upper-case letters)
      (                 # Followed by either
        \s+[A-Z][A-Z]+  # Another upper-case word
        |               # or
        !\z             # An exclamation mark at the end of the string
      )
    /x
  end

  def question
    /\?\z/
  end

  def silence
    /\A\s*\z/
  end
end
