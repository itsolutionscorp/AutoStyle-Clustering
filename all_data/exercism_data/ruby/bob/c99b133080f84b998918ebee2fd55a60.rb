class Bob
  def silence?(string)
    string.size == 0 || string =~ /^ +$/
  end

  def question?(string)
    string[-1] == '?'
  end

  def shouting?(string)
    string.upcase == string && string =~ /[a-zA-Z]/
  end

  def hey(message)
    if shouting? message
      'Woah, chill out!'
    elsif question? message
      'Sure.'
    elsif silence? message
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
