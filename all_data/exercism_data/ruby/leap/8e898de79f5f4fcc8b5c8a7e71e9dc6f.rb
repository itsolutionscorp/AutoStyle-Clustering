class Year

	class << self
	
		def leap?(year)
			fourYear?(year) && !( hundredYear?(year) && !fourHundredYear?(year) )
		end

		private

			def fourYear?(year)
				year % 4 == 0
			end

			def hundredYear?(year)
				year % 100 == 0
			end

			def fourHundredYear?(year)
				year % 400 == 0
			end
	end

end
