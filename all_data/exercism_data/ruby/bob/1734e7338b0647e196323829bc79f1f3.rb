class Bob
  def hey(arg)

    if arg =~ /^\s*$/ || arg.nil?
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
