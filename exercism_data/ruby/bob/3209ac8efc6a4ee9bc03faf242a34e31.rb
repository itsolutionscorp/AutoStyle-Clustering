class Bob
  def hey(input)
    if empty?(input)
      'Fine. Be that way.'
    elsif question?(input)
      'Sure.'
    elsif yelling?(input)
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private

  def empty?(input)
    input.nil? || input.length == 0
  end

  def question?(input)
    input.end_with?('?')
  end

  def yelling?(input)
    input == input.upcase
  end
end
