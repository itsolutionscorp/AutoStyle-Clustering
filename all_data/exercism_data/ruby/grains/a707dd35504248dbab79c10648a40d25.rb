class Grains 
	attr_accessor :total
	
	def square(number)
		return 2**(number-1)
	end

	def total
		return self.square(65)-1
	end


# def square(number)
# 	square = 1
# 	s = 1
# 	until square < number
# 	1..number.each do |x| return
# 	s = s*2 

end
