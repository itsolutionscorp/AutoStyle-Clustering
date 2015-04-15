class Gigasecond
	def self.from(date)
		date_in_seconds = date.to_time.to_i
		gs_anniversary = Time.at( (date_in_seconds + 1000000000) )

		puts "Your gigasecond anniversary will be on: #{gs_anniversary}"
		return gs_anniversary
	end
end
