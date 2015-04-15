class Year
  def self.leap? num
	  if num.modulo(4).zero?
	  	if num.modulo(100).zero?
	  		if num.modulo(400).zero?
	  			return true
	  		end
	  		return false
	  	end
	  	return true
	  end
  end
end
