class Bob
  def hey mesg
    if mesg.gsub(' ','').size == 0
      'Fine. Be that way!'
    elsif mesg.upcase == mesg
      'Woah, chill out!'  
    elsif mesg[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
