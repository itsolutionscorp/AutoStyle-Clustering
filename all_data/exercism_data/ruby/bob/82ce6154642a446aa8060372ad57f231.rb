class Bob
  def hey(statement)
    if silence?(statement)
      'Fine. Be that way!'
    elsif yell?(statement)
      'Woah, chill out!'
    elsif question?(statement)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def silence?(statement)
    statement.strip.empty?
  end

  def question?(statement)
    statement.end_with?('?')
  end

  def yell?(statement)
    statement == statement.upcase
  end
end
