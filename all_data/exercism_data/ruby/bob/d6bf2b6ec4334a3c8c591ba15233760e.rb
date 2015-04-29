class Bob
  def hey(arg)
    return 'Whoa, chill out!' if arg =~ /[a-zA-Z]/ && arg == arg.upcase
    return 'Sure.' if arg =~ /\?\z/
    return 'Whatever.' if arg =~ /[0-9a-f]/
    'Fine. Be that way!'
  end
end
