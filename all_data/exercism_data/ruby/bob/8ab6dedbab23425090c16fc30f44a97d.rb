class Bob
  def hey mesg
    if mesg.strip.size == 0
      'Fine. Be that way!'
    elsif mesg == mesg.upcase
      'Woah, chill out!'  
    elsif mesg[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
