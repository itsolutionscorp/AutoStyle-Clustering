# Not clear on why this isn't working, any feedback?

class String
  
  def shouting?
    if self == self.upcase
      true
    else
      false
    end
  end

  def questioning?
    if self.split('').last == '?'
      true
    else
      false
    end
  end

  def silent?
    if self == '' || self == nil
      true
    else 
      false
    end
  end

  def talking?
    if self.shouting? == false && self.questioning? == false && self.silent? == false
      true
    else
      false
    end
  end

end

class Bob

	def hey input
		case input
		when input.shouting?
		  return 'Woah, chill out!'
		when input.questioning?
			return 'Sure.'
		when input.talking?
			return 'Whatever.'
		when input.silent?
			return 'Fine. Be that way.'
		end
	end

end
