require 'prime'

class Raindrops
  def self.factors(num)
  	Prime.prime_division(num).flatten
  end
  
  def self.convert(num)
  	testArray = factors(num)
  	msg = ""
  	has3 = false
  	has5 = false
  	has7 = false
  	
  	testArray.each do |testNum|
  		case testNum
  		  when 3
  		  	has3 = true
  		  when 5
  		  	has5 = true
  		  when 7
  		  	has7 = true
  		end
  	end
  	
  	if has3
  		msg << 'Pling'
  	end
  	if has5
  		msg << 'Plang'
  	end
  	if has7
  		msg << 'Plong'
  	end
  	
  	msg.empty?? num.to_s : msg
  	
  end
end
