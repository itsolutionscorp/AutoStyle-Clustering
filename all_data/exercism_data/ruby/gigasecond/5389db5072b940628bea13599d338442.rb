class Gigasecond
	def self.from(date)
		gigasecond = 10**9
		Date.strptime((date.strftime('%s').to_i + gigasecond).to_s,'%s')
	end
end
