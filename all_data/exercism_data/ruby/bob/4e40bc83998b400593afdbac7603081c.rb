class Bob
  def hey(arg)
    return 'Whatever.' if arg.class != String

    if arg =~ /^\s*$/
      'Fine. Be that way!'
    elsif arg.upcase === arg
      'Woah, chill out!'
    elsif arg.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
