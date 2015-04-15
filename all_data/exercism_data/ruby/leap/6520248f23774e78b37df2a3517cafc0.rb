class Year
	
	def self.leap?(year)
		fourYear?(year) && !( hundredYear?(year) && !fourHundredYear?(year) )
	end

	private

		def self.fourYear?(year)
			year % 4 == 0
		end

		def self.hundredYear?(year)
			year % 100 == 0
		end

		def self.fourHundredYear?(year)
			year % 400 == 0
		end

end
