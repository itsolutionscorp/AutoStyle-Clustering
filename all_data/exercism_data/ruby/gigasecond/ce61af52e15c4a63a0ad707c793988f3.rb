class Gigasecond
	GIGASECOND = 10**9
	def self.from(date)
		Date.strptime((date.strftime('%s').to_i + GIGASECOND).to_s,'%s')
	end
end
