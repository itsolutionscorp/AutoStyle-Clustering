class Bob

  def hey(string)
    if string == string.upcase && string =~ /[a-zA-Z]/
      'Woah, chill out!'
    elsif string[-1] == '?'
      'Sure.'
    elsif !(string =~ /\w/)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
