class Fixnum
	def to_roman
		val = self
		res = ""
		num_ary = self.to_s.split("")
		# res += "I" if num_ary[-1].to_i % 10 == 1
		# res += "II" if num_ary[-1].to_i % 10 == 2
		# res += "III" if num_ary[-1].to_i % 10 == 3
		# res += "IV" if num_ary[-1].to_i % 10 == 4
		# res += "V" if num_ary[-1].to_i % 10 == 5	
		# res += "VI" if num_ary[-1].to_i % 10 == 5	
		# res += "VII" if num_ary[-1].to_i % 10 == 5	
		# res += "IIX" if num_ary[-1].to_i % 10 == 5	
		# res += "IX" if num_ary[-1].to_i % 10 == 5					
		while val -1000 >= 0
			res += "M" 
			val -= 1000
		end
		while val -500 >= 0
			res += "D" 
			val -= 500
		end
		while val -100 >= 0
			res += "C" 
			val -= 100
		end
		while val -50 >= 0
			res += "L" 
			val -= 50
		end
		while val -10 >= 0
			res += "X" 
			val -= 10
		end
		while val -5 >= 0
			res += "V" 
			val -= 5
		end		
		while val -1 >= 0
			res += "I" 
			val -= 1
		end
		res
		# if self.split == 1
		# 	"I"
		# end
	end

end
