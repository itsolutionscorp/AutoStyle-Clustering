class Bob
  def hey str
    @str = str
    if upcase? str
    'Woah, chill out!'
    else
    'Whatever.'
  end
  
  def upcase? str
    if str == str.upcase {true}
    false
  end    
  
  
end
