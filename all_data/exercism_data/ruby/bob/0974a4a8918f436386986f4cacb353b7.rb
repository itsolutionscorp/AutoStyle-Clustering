class Bob

  def hey(string)
    string.strip!
    
    case
    when string == string.upcase && string =~ /[a-zA-Z]/
      'Woah, chill out!'
    when string[-1] == '?'
      'Sure.'
    when !(string =~ /\w/)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

end
