class Bob
  def hey(arg)
    if arg.to_s == ''
      "Fine. Be that way!"
    elsif arg.to_s.upcase == arg
      "Woah, chill out!"
    elsif arg.to_s.chars.last == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
