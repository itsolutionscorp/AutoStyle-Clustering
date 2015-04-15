class Bob
  def hey(arg)
    return "Fine. Be that way!"  if arg.nil? || arg.empty?
    return "Sure."               if arg.end_with? "?"
    return "Woah, chill out!"    if arg == arg.upcase
    return "Whatever."
  end
end
