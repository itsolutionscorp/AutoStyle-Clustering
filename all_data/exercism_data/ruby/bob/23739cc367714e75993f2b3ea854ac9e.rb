class Bob
  def hey(phrase)
    if silence?(phrase)
      'Fine. Be that way!'
    elsif yelling?(phrase)
      'Whoa, chill out!'
    elsif question?(phrase)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def question?(str)
    str[-1] == '?'
  end

  def yelling?(str)
    str.upcase == str && str =~ /[A-Z]/
  end

  def silence?(str)
    str.strip.empty?
  end

end
