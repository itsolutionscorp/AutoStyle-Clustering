class Bob
  def hey(str)
    case
    when (str==NIL or str.strip.empty?)
      'Fine. Be that way!'
    when str.upcase == str
      'Woah, chill out!'
    when str[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
