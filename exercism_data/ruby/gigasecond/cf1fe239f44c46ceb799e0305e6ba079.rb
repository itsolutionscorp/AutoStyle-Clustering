class Gigasecond

	def self.from org_time


		converted_time = org_time.to_time

		gigatime = converted_time + 10**9

		gigatime.to_date
		
	end



end
