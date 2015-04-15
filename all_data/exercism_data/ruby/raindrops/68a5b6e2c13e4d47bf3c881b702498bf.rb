class Raindrops
	def self.convert(num)
		str=''
		if num%3==0
			str=str+'Pling'
		end
		
		if num%5==0
			str=str+'Plang'
		end
		
		if num%7==0
			str=str+ 'Plong'
		end
		
		if str==""
			return  num.to_s
		else
			return str	
		end
	end
	
end

#puts Raindrops.convert(1)
