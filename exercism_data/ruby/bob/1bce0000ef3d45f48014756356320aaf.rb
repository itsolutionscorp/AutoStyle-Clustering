class Bob
  def hey(arg)
    if arg.upcase == arg && !!arg.match(/[a-zA-Z]/)
      'Woah, chill out!'
    elsif arg[-1] == "?"
      "Sure."
    elsif arg.delete(' ') == ""
      'Fine. Be that way!'
    else
      "Whatever."
    end
  end
end
