class Bob
  def hey(input)
    case
    when empty?(input)
      'Fine. Be that way.'
    when question?(input)
      'Sure.'
    when yelling?(input)
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private

  def empty?(input)
    input.nil? || input.empty?
  end

  def question?(input)
    input.end_with?('?')
  end

  def yelling?(input)
    input == input.upcase
  end
end
