class Gigasecond

	def self.from(data)
		data = (data.to_time + (10**9)).to_date

		return data
	end
end
