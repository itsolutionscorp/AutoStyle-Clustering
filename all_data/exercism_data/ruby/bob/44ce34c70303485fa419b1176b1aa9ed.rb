class Bob
  def hey(argument)
    if argument == ""
      "Fine. Be that way!"
    elsif argument == argument.upcase
      "Woah, chill out!"
    elsif argument.end_with?("?") == true
      "Sure."
    else
      "Whatever."
    end
  end
end
