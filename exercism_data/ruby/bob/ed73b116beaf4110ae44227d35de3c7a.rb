class Bob

  def hey(statement)
    case
    when silence?(statement)
      'Fine. Be that way.'
    when shouting?(statement)
      'Woah, chill out!'
    when question?(statement)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silence?(s)
    s == ''
  end

  def question?(s)
    s.end_with?('?')
  end

  def shouting?(s)
    s == s.upcase
  end

end
