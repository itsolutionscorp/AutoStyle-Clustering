class Bob
  def hey(arg)
    return 'Fine. Be that way.' if arg.nil? || arg.empty?
    return "Sure." if arg[-1] == '?'
    return "Woah, chill out!" if arg.upcase == arg
    "Whatever."
  end
end
