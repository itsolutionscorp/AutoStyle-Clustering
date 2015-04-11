class Bob
  
  def hey(string)
    if string == nil || string == ''
      'Fine. Be that way!'
    elsif string == string.upcase
      'Woah, chill out!'
    elsif string.end_with?("?")
      'Sure.'
    else
      'Whatever.'
    end
  end
  
end
