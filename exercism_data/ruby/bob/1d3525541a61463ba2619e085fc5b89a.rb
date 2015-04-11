class Bob

  def hey(statement)
    case statement
    when /\A \s* \z/x
      'Fine. Be that way!'
    when /\A [^[:lower:]]+ \z/x
      'Woah, chill out!'
    when /\?\z/
      'Sure.'
    else
      'Whatever.'
    end
  end

end
