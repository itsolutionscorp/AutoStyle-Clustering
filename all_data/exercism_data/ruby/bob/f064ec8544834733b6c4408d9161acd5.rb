class Bob

  def hey(statement)
    case
    when silence?(statement)
      'Fine. Be that way!'
    when yelling?(statement)
      'Woah, chill out!'
    when questioning?(statement)
      'Sure.'
    else
      'Whatever.'
    end
  end

private

  def silence?(statement)
    statement.strip.empty?
  end

  def yelling?(statement)
    statement.upcase == statement
  end

  def questioning?(statement)
    statement.end_with?('?')
  end

end
