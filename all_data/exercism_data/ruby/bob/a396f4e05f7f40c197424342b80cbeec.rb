class Bob

  attr_reader :interlocution

  def hey(interlocution)
    @interlocution = interlocution

    case
    when shouting
      "Woah, chill out!"
    when questioning
      "Sure."
    when silent
      'Fine. Be that way!'
    else
      "Whatever."
    end
  end

  def shouting
    interlocution.upcase == interlocution && interlocution =~ (/[A-Z]/)
  end

  def questioning
    interlocution[-1] == "?"
  end

  def silent
    interlocution.strip.empty?
  end

end
