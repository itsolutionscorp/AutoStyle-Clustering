class Bob
  def hey(phrase)
    if silent?(phrase)
      'Fine. Be that way!'
    elsif shouted?(phrase)
      'Woah, chill out!'
    elsif question?(phrase)
      'Sure.'
    else
      'Whatever.'
    end
  end

private

  def silent?(string)
    string.nil? || string =~ /^\s*$/
  end

  def shouted?(string)
    !(string =~ /[[:lower:]]/)
  end

  def question?(string)
    string =~ /.*\?$/
  end
end
