class Gigasecond
	def self.from(bday)
		Time.at(bday.to_time.to_i + 1_000_000_000).to_date
	end
end
