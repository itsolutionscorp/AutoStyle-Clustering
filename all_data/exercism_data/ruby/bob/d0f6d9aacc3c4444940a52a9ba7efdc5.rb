class Bob
  def hey(saywhat)
    if silence?(saywhat)
      "Fine. Be that way!"
    elsif yelling?(saywhat)
      "Whoa, chill out!"
    elsif question?(saywhat)
      "Sure."
    else
      "Whatever."
    end
  end

  def silence?(saywhat)
    saywhat.strip.empty?
  end

  def yelling?(saywhat)
    saywhat =~ /[A-Z]/ && saywhat !~ /[a-z]/
  end

  def question?(saywhat)
    saywhat[-1] == '?'
  end
end
