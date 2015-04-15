class Gigasecond

	def self.from(date)
		Time.at(future(date)).to_date
	end

	def self.future(date)
		date.to_time.to_i + 1000000000
	end

end
