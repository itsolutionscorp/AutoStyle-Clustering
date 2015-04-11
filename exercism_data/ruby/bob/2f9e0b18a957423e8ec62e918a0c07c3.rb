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

  private

  def shouting?(statement)
    upcase?(statement) && includes_letters?(statement)
  end

  def question?(statement)
    statement.end_with?('?')
  end

  def silence?(statement)
    statement.strip.empty?
  end

  def upcase?(statement)
    statement.upcase == statement
  end

  def includes_letters?(statement)
    statement.downcase != statement
  end
end
