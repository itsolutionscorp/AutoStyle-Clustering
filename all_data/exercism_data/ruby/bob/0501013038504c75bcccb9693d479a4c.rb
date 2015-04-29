class Bob
  def statement?(message)
    message =~ /\.$/
  end

  def shouting?(message)
    message =~ /[A-Z[%^\*@#\$\(1]]+!?$/
  end

  def question?(message)
    message =~ /\?\Z/
  end

  def forceful?(message)
    message =~ /^[A-Z]?[^A-Z]+!$/
  end

  def forceful_question?(message)
    message =~ /^[A-Z\s]+\?$/
  end

  def silent?(message)
    message == '' || message =~ /^\s+$/
  end

  def hey(message)
    if shouting?(message) || forceful_question?(message)
      'Woah, chill out!'
    elsif question?(message)
      'Sure.'
    elsif silent?(message)
      'Fine. Be that way!'
    elsif statement?(message) || forceful?(message)
      'Whatever.'
    else
      'Whatever.'
    end
  end
end
