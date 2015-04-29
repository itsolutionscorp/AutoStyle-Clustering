class Gigasecond
	def self.from (birth_date)
		calculate_gigasecond_anniversary birth_date
	end

	private
	def self.calculate_gigasecond_anniversary (birth_date)
		(Time.at add_gigasecond birth_date).to_date
	end
	def self.seconds_since (date)
		date.to_time.to_i
	end
	def self.add_gigasecond (date)
		(seconds_since date) + 10**9
	end
end
