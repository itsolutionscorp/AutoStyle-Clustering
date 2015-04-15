class Bob

  def hey (statement)

    if blank?(statement)
      'Fine. Be that way!'
    elsif yelling?(statement)
      'Woah, chill out!'
    elsif question?(statement)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def blank?(statement)
    statement.strip.empty?
  end

  def yelling?(statement)
    statement =~ /[[:alpha:]]/ && statement == statement.upcase
  end

  def question?(statement)
    statement.end_with?('?')
  end

end
