class Bob
  def hey(statement)
    if shouting?(statement)
      'Woah, chill out!'
    elsif question?(statement)
      'Sure.'
    elsif forceful?(statement)
      'Whatever.'
    elsif silence?(statement)
      'Fine. Be that way.'
    else
      'Whatever.'
    end
  end

  def shouting?(statement)
    statement =~ /\A[A-Z\d\W]+\z/
  end

  def question?(statement)
    statement =~ /\A[a-zA-Z\d\W]+\?\z/
  end

  def forceful?(statement)
    statement =~ /\A[a-zA-Z\d\W]+\!\z/
  end

  def silence?(statement)
    statement == '' || statement == nil
  end
end
