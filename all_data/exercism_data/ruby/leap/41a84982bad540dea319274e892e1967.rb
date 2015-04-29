class Year
	def self.leap? (año)
		if año.modulo(400) == 0 && año.modulo(4) == 0
			return true
		end
		if año.modulo(100) != 0
			return true
		end
	end
end
