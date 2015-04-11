class Bob

  def hey(statement)
    case
    when statement == statement.upcase && statement.match(/[a-zA-Z]/)
      'Woah, chill out!'
    when last_char(statement) == '?'
      'Sure.'
    when statement == '' || statement[0,1] == ' '
      'Fine. Be that way!'
    else
      "Whatever."
    end
  end

  def last_char(statement)
    statement[statement.length-1]
  end

end
