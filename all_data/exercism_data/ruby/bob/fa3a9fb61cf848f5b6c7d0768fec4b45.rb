class Bob
  def hey(str)
    if is_blank?(str)
      'Fine. Be that way!'
    elsif is_upcase?(str)
      'Woah, chill out!'
    elsif is_question?(str)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def is_blank?(str)
    !str || /\A\S*\Z/ === str
  end

  def is_upcase?(str)
    str.upcase == str
  end

  def is_question?(str)
    str[-1] == '?'
  end
end
