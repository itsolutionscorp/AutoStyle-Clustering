class Bob
  def hey(arg)
    return "Sure."               if arg.end_with? "?"
    return "Woah, chill out!"    if arg == arg.upcase
    return "Fine. bet that way!" if arg.nil?
    return "Whatever."
  end
end
