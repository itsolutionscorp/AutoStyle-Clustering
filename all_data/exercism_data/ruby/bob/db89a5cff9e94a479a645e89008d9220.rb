class Bob
  def hey(string)
    if(/[A-Za-z]/.match(string) && string.upcase == string)
      return 'Woah, chill out!'
    elsif (string[string.length-1] == "?")
      return 'Sure.'
    elsif (string.length == 0 || string[0] == " ")
      return 'Fine. Be that way!'
    else
      return 'Whatever.'
    end
  end
end
