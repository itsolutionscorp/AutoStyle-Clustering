class Hamming
	# def initialize (s1, s2)
	# 	@s1 = s1
	# 	@s2 = s2
	# end

	def self.compute(s1, s2)
		a1 = s1.split(//)
		a2 = s2.split(//)
		counter = 0
		
		([a1.length, a2.length].min).times do |count|
			if a1[count] != a2[count]
				counter = counter + 1
			end
		end

		return counter

	end
end
