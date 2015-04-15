class Gigasecond
	def self.from(date)
		Date.strptime((date.to_time.to_i + 10**9).to_s, '%s')
	end
end
