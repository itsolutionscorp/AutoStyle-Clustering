class Bob

  def hey(msg)
    if silent?(msg)
      'Fine. Be that way!'
    elsif asking?(msg)
      'Sure.'
    elsif shouting?(msg)
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

private

  def silent?(msg)
    msg =~ /\A\s*\z/
  end

  def asking?(msg)
    msg =~ /[\da-z].*\?\z/
  end

  def shouting?(msg)
    msg =~ /[A-Z]/ && msg !~ /[a-z]/
  end

end
