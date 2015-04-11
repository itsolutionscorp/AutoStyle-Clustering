class Bob

  def hey(statement)

    case
    when shouting?(statement)
      "Woah, chill out!"
    when asking?(statement)
      "Sure."
    when silence?(statement)
      "Fine. Be that way!"
    else
      "Whatever."
    end

  end


  def shouting?(s)
    s.upcase == s && /[A-Z]/.match(s)
  end

  def asking?(s)
    s.end_with?("?")
  end

  def silence?(s)
    s.empty? || /\s{3,}/.match(s)
  end

end
