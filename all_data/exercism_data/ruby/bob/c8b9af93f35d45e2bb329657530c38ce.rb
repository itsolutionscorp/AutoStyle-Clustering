class Bob
  def hey(arg)
    return 'Whoa, chill out!' if arg == arg.upcase && arg =~ /[a-zA-Z]/
    return 'Sure.' if arg =~ /\?\z/
    return 'Whatever.' if arg =~ /[0-9a-f]/
    'Fine. Be that way!'
  end
end
