class Bob
  def hey(arg)
    if
      arg == arg.upcase
      "Woah, chill out!"
    elsif
      arg.end_with?("?")
      "Sure."
    elsif
      arg.is_a? Integer
      "Sure."
    else
      "Whatever."
    end
  end
end
