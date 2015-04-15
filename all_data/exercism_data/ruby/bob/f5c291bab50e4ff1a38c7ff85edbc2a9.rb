class Bob
  def hey(input)
    input = input.to_s

    if saying_nothing? input
      'Fine. Be that way.'
    elsif yelling? input
      'Woah, chill out!'
    elsif question? input
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def saying_nothing?(str)
    str.empty?
  end

  def yelling?(str)
    str == str.upcase
  end

  def question?(str)
    str.end_with? '?'
  end
end
