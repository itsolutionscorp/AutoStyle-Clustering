class Bob
  def hey(statement)
    case
    when empty?(statement)
      'Fine. Be that way.'
    when yelling?(statement)
      'Woah, chill out!'
    when question?(statement)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def question?(input)
    input.end_with?('?')
  end

  def empty?(input)
    input.to_s.empty?
  end

  def yelling?(input)
    input == input.upcase
  end
end
