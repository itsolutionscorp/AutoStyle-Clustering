class Bob
  def hey(message)
    if shouting?(message)
      'Woah, chill out!'
    elsif question?(message)
      'Sure.'
    elsif blank?(message)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def blank?(message)
    message =~ /\A *\Z/
  end

  def question?(message)
    message =~ /\?\Z/
  end

  def shouting?(message)
    message =~ /[A-Z]/ && message !~ /[a-z]/
  end
end
