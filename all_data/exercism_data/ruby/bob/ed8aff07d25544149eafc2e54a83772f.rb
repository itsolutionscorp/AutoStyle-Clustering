class Bob
 
  SURE      = 'Sure.'
  CHILL_OUT = 'Whoa, chill out!'
  FINE      = 'Fine. Be that way!'
  WHATEVER  = 'Whatever.'

  def hey(remark)
    # remove unnecessary characters
    remark_chars = remark.gsub(/[^a-zA-Z0-9?]+/, '')

    #poke at the string and see it's characteristics
    is_yelling = remark_chars.upcase.eql?(remark_chars)
    is_question = remark_chars[-1] == '?'
    is_digits = remark.gsub(/[^0-9?]/, '').eql?(remark_chars)
  
    # boom...LOGIC
    if remark_chars.length == 0
      FINE 
    elsif is_question && (!is_yelling || is_digits)
      SURE
    elsif is_yelling && !is_digits
      CHILL_OUT
    else
      WHATEVER
    end
  end

end
