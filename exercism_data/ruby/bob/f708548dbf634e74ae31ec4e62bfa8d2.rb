class Bob

  def hey(str)
  	reply = ''
  	case
      when silence?(str)
      	reply = 'Fine. Be that way!'  		
  	  when yell?(str)
        reply = 'Woah, chill out!'
  	  when question?(str)
  	    reply = 'Sure.'
  	  else
  	  	reply = 'Whatever.'
  	end
    reply
  end
  
  def question?(str)
    str.split('')[-1] == '?'
  end

  def yell?(str)
    str.upcase == str
  end
 
  def silence?(str)
  	str.strip == ''
  end

end
