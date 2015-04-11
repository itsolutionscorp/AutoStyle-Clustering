class Bob
  def hey(saywhat)
    case saywhat
    when saywhat.upcase # this is okay, 
      return 'Whoah, chill out!'
    when /\?$/ # but this is ugly
      return 'Sure.'
    when /^\s*$/ # and this is worse
      return 'Fine. Be that way!'
    else
      return 'Whatever.'
    end
  end
end
