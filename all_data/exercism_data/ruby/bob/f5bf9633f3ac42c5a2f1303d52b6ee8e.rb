class Bob
  def hey(input)
    if blank? input
      'Fine. Be that way.'
    elsif exclamation? input
      'Woah, chill out!'
    elsif question? input
      'Sure.'
    else
      'Whatever.'
    end
  end

  def blank?(str)
    str.nil? || str.empty?
  end

  def exclamation?(str)
    str !~ /[a-z]/
  end

  def question?(str)
    str.end_with? '?'
  end
end
