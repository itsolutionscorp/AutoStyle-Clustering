class Bob

  # This is my concise, very unexpressive first submission with tests passing.
  def hey(statement)
    case
    when statement.strip == ''
      'Fine. Be that way!'
    when statement.upcase == arg && arg =~ /[A-Za-z]/
      'Woah, chill out!'
    when (statement =~ /(.*)\?$/) && !(statement =~ /\n/)
      'Sure.' 
    else
      'Whatever.'
    end
  end
end
