class Bob

  def hey(talk)
    case 
    when silent?(talk)
        "Fine. Be that way."
    when question?(talk)
        "Sure."
    when shouting(talk)
        "Woah, chill out!"
    else "Whatever."
    end
  end

  def silent?(talk)
    talk.empty?
  end

  def question?(talk)
    talk.end_with?('?')
  end

  def shouting(talk)
    talk == talk.upcase
  end
end

# Thank you for making me refactor :)
