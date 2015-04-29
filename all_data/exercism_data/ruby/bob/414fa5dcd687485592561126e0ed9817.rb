class Bob
  def hey(arg)
    if
      arg == arg.upcase
      "Woah, chill out!"
    elsif
      arg == arg.include?("?")
      "Sure."
    else
      "Whatever."
    end
  end
end
