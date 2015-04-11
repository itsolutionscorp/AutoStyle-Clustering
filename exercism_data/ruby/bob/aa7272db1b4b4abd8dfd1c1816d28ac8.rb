class Bob

  def hey(statement)
    if silence?(statement)
      ret = fine
    elsif shouting?(statement)
      ret = chill_out
    elsif question?(statement)
      ret = sure
    end

    ret || whatever
  end

  def silence?(statement)
    statement.nil? || statement.empty?
  end

  def shouting?(statement)
    statement.upcase == statement
  end

  def question?(statement)
    statement =~ /\?$/
  end

  def whatever
    'Whatever.'
  end

  def chill_out
    'Woah, chill out!'
  end

  def sure
    'Sure.'
  end

  def fine
    'Fine. Be that way!'
  end

end
