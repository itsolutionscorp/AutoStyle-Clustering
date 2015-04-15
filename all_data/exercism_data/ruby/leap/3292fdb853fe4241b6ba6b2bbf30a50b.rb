class Year
	LEAP_HASH = {
		400 => true,
		100 => false,
		4 => true,
		1 => false,
	}

	def self.leap?(year)
		year_type = LEAP_HASH.find { |k, v| (year % k == 0) } [1]
	end
end
