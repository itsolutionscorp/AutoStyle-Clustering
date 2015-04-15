class Bob
  def hey(stating_argument)
    if yelling?(stating_argument)
      'Woah, chill out!'
    elsif question?(stating_argument)
      'Sure.'
    elsif nothing?(stating_argument)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  def yelling?(statement)
    statement == statement.upcase && statement != statement.downcase
  end

  def question?(statement)
    statement.end_with?('?')
  end

  def nothing?(statement)
    statement.strip.empty?
  end
end
