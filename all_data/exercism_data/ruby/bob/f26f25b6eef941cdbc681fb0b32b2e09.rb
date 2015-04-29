class Bob
  def hey(statement)
    if shouting?(statement)
      'Woah, chill out!'
    elsif question?(statement)
      'Sure.'
    elsif silence?(statement)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def shouting?(statement)
    statement == statement.upcase && statement =~ /[A-Z]/
  end

  def question?(statement)
    statement.end_with?('?')
  end

  def silence?(statement)
    statement.strip.empty?
  end
end
