class Bob
 
  SURE      = 'Sure.'
  CHILL_OUT = 'Whoa, chill out!'
  FINE      = 'Fine. Be that way!'
  WHATEVER  = 'Whatever.'

  def hey(remark)

    # boom...LOGIC
    if remark.strip.length == 0
      FINE 
    elsif remark.upcase.eql?(remark) && !remark.downcase.eql?(remark)
      CHILL_OUT
    elsif remark[-1] == '?'
      SURE
    else
      WHATEVER
    end
  end

end
