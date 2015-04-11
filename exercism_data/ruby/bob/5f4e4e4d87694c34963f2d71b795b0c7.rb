class Bob
  def hey(arg)
    if arg == arg.upcase && arg =~ /[a-zA-Z]/
      'Whoa, chill out!'
    elsif arg =~ /\?\z/
      'Sure.'
    elsif arg =~ /[0-9a-f]/
      "Whatever."
    else
      'Fine. Be that way!'
    end
  end
end
