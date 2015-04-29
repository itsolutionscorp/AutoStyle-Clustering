class Bob
  def hey mesg
    case 
    when mesg.strip.size == 0
      'Fine. Be that way!'
    when mesg == mesg.upcase
      'Woah, chill out!'  
    when mesg[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
