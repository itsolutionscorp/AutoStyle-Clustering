class Gigasecond
	def self.from(date)
		Date.strptime((DateTime.parse(date.to_s).to_time.to_i + 1000000000).to_s,'%s')
	end
end
