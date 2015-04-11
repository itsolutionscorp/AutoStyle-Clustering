class Bob
  def hey(statement)
    if shouting?(statement)
      'Woah, chill out!'
    elsif question?(statement)
      'Sure.'
    elsif blank?(statement)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  def shouting?(statement)
    statement.upcase == statement && statement.downcase != statement
  end

  def question?(statement)
    statement.end_with?('?')
  end

  def blank?(statement)
    statement.strip.empty?
  end
end
