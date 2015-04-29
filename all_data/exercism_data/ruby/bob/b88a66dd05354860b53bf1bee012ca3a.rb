class Bob
  def hey(statement)
    if isSilent? statement
      return 'Fine. Be that way!'
    end

    if isShouting? statement
      return 'Woah, chill out!'
    end

    if isQuestion? statement
      return 'Sure.'
    end

    return 'Whatever.'
  end

  def isSilent? statement
    return statement.strip == ''
  end

  def isShouting? statement
    return statement.upcase == statement
  end

  def isQuestion? statement
    return statement.strip.end_with? '?'
  end
end
