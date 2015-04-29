class Bob
  def hey statement
    return 'Fine. Be that way!' if silent? statement
    return 'Woah, chill out!'   if yell? statement
    return 'Sure.'              if question? statement
    return 'Whatever.'
  end
  def silent? statement
    statement.strip == ''
  end
  def yell? statement
    statement == statement.upcase
  end
  def question? statement
    statement.end_with? '?'
  end
end
