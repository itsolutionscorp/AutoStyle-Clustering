class Gigasecond
	def self.from(from_date)
		Time.at(from_date.to_i + 10**9).utc
	end
end
