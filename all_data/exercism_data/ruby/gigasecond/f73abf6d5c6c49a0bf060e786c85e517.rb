class Gigasecond
	def self.from(birthday)
		t = birthday.to_time + (1000000000)
		t.to_date
	end
end
