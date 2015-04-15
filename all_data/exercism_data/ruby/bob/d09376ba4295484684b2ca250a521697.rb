class Bob
  def hey(arg)
    return 'Fine. Be that way!' if arg.strip.empty?
    return "Woah, chill out!" if arg.upcase == arg
    return 'Sure.' if arg[-1] == '?'
    "Whatever."
  end
end
