class Bob
  def hey(statement)

    if is_silent? statement
      return 'Fine. Be that way!'

    elsif is_shouting? statement
      return 'Woah, chill out!'

    elsif is_question? statement
      return 'Sure.'

    else
      return 'Whatever.'
    end

  end

  def is_silent? statement
    return statement.strip == ''
  end

  def is_shouting? statement
    return statement.upcase == statement
  end

  def is_question? statement
    return statement.strip.end_with? '?'
  end
end
