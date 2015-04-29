class Gigasecond
	def self.from birthday
		(birthday.to_time + 10**9).to_date
	end
end
