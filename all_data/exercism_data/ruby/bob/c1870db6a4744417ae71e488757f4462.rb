class Bob
  def hey(arg)
    if arg =~ /^\s*$/
      'Fine. Be that way!'
    elsif arg.upcase === arg
      'Woah, chill out!'
    elsif arg =~ /.*\?$/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
