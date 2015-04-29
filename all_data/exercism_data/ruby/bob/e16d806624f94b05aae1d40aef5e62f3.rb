class Bob

  def hey(arg)
    if arg.strip == ""
      "Fine. Be that way!"
    elsif arg == arg.upcase 
      "Woah, chill out!"
    elsif arg[-1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
