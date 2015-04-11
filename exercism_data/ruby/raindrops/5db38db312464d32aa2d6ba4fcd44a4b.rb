require 'Prime'

class Raindrops
	def self.convert(num)
		prime_factors = []
 		index = 2
  	while num > 1
    	if num % index == 0
      	prime_factors << index
      	num = (num / index)
    	else
      	index += 1
    	end
  	end
  	s = []
	  if prime_factors.include?(3)
	  	s << "Pling"
	  	if prime_factors.include?(5)
	  		s << "Plang"
	  	elsif prime_factors.include?(7)
	  		s << "Plong"
	  	end
	  elsif prime_factors.include?(5)
	  	s << "Plang"
	  	if prime_factors.include?(7)
	  		s << "Plong"
	  	end
	  elsif prime_factors.include?(7)
	  	s << "Plong"
	  else
	  	return s << num.to_s
	  end
	  s.join
	end
end

Raindrops.convert(52)
