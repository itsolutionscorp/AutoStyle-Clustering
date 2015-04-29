class Bob

  def hey(statement)
    case statement
    when /\A[[:space:]]*\z/
      'Fine. Be that way!'
    when /\A[^[:lower:]]*\z/
      'Woah, chill out!'
    when /\?\z/
      'Sure.'
    else
      'Whatever.'
    end
  end

end
