class Fixnum

	def to_roman
		num = self
		digits = [0, 0, 0, 0]
		digits.concat(num.to_s.split(//))
		digits.delete_if{|x| digits.length > 4}
		digits.collect! {|x| x.to_i}

		i=0
		romans = ["M","","C","D","X","L","I","V"]
		string = ""
		
		digits.each do |x|
	
			case x
			when 1..3 
				string += romans[i]*x
				i+=2		
			when 4
				string += romans[i]+romans[i+1]
				i+=2
				
			when 5
				string += romans[i+1]
				i+=2
			when 6..8
				string += romans[i+1]+romans[i]*(x-5)         
				i+=2
			when 9
				string += romans[i]+romans[i-2]
				i+=2
			else	
				i+=2
			end
		end
			
		string
	
	end
end
