class Gigasecond

	def self.from(start_date)
		future = start_date.to_time.to_i + (10**9)
		return Time.at(future).to_date
	end

end
