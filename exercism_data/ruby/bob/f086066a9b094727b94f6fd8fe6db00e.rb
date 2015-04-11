class Bob
  
  def hey something
    if something == nil or something.strip == ""
      'Fine. Be that way!'
    elsif something == something.upcase
      'Woah, chill out!'
    elsif something.end_with? "?"
      'Sure.'  
    else
      'Whatever.'
    end
  end
  
end
