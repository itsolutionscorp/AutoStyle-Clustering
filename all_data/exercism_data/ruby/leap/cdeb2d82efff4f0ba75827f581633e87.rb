class Year
	def self.leap?(year)
		year % 4 == 0 && (century?(year) || fourth_century?(year))
	end

	def self.century?(year)
		year % 100 != 0
	end

	def self.fourth_century?(year)
		year % 400 == 0
	end
end
