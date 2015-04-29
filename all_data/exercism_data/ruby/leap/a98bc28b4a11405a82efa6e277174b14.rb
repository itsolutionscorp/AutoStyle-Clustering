class Year

	def self.leap?(year)
		year % 4 == 0 unless self.century_except_fourth_century?(year)
	end

	private
	def self.century_except_fourth_century?(year)
		year % 100 == 0 unless year % 400 == 0
	end

end
