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

  def question?(phrase)
    phrase =~ /.*\?\z/
  end

  def yelling?(phrase)
    phrase =~ /^[[A-Z\d\^@#\$%\(]+[, ]*[A-Z\d\^@#\$%\(]]+[A-Z][\d!\?\.]*$/
  end

  def silence?(phrase)
    phrase =~ /^[ \t]*\z/
  end
end
