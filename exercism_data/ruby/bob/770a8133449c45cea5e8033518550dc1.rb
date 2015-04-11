class Bob

  def hey(str)
    case
    when is_empty?(str)
      'Fine. Be that way!'
    when is_screaming?(str)
      'Woah, chill out!'
    when is_asking?(str)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def is_empty?(str)
    str.strip.empty?
  end

  def is_screaming?(str)
    str =~ /[[:alpha:]]/ && str == str.upcase
  end

  def is_asking?(str)
    str[-1] == '?'
  end

end
