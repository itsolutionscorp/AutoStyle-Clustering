class Bob
  def hey(saywhat)
    if saywhat.upcase == saywhat
      return 'Woah, chill out!'
    elsif saywhat[-1] == '?'
      return 'Sure.'
    elsif saywhat.strip.empty?
      return 'Fine. Be that way!'
    else
      return 'Whatever.'
    end
  end
end
