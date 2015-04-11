class Bob

  # This is my concise, very unexpressive first submission with tests passing.
  def hey(arg)
    if arg.strip == ''
      'Fine. Be that way!'
    elsif arg.upcase == arg
      'Woah, chill out!'
    elsif (arg =~ /(.*)\?$/) && !(arg =~ /\n/)
      'Sure.' 
    else
      'Whatever.'
    end
  end
end
