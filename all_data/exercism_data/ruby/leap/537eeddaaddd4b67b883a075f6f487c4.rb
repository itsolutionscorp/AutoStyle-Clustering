class Year
	def self.leap? year
		if (year % 4).eql?(0) and (!(year % 100).eql?(0) or (year % 400).eql?(0))
			true
		else
			false
		end
	end
end
