class Fixnum
	def multiple_of?(number)
		self.modulo(number) == 0
	end
end

class Year
	def self.leap?(year)
		(year.multiple_of?(4) && !year.multiple_of?(100)) || year.multiple_of?(400)
	end
end
