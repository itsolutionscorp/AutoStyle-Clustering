class Bob

  def hey(statement)
    case
    when yelling_is_involved?(statement)
      'Woah, chill out!'
    when question?(statement)
      'Sure.'
    when passive_aggressive?(statement)
      'Fine. Be that way!'
    else
      "Whatever."
    end
  end

  def last_char(statement)
    statement[statement.length-1]
  end

  def yelling_is_involved?(statement)
    statement == statement.upcase && statement.match(/[a-zA-Z]/)
  end

  def passive_aggressive?(statement)
    statement == '' || statement[0,1] == ' '
  end

  def question?(statement)
    last_char(statement) == '?'
  end

end
