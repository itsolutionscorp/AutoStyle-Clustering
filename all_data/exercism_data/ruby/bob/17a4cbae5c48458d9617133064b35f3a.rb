class Bob
  
  def initialize()
  end
  
  def hey(msg)
    if !msg || (msg.empty? == true)
      'Fine. Be that way!'
    elsif msg == msg.upcase 
      "Woah, chill out!" 
    elsif(/[?]\Z/.match(msg) )
      "Sure."
    else 
      'Whatever.'
   end
  end
end
