class Gigasecond
	@@gs = 10**9

	def self.from(date)
		Time.at(date.to_time.to_i + @@gs).to_date
	end

end
