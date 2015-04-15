class Bob

  def hey(statement)
    length = statement.length unless statement.nil?

    case 
    when statement.nil? || statement.strip == ""
      'Fine. Be that way.'
    when statement.upcase == statement
      'Woah, chill out!'
    when statement[length - 1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end

end
