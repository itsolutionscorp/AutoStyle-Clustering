class Gigasecond
	class << self
		def from(date)
			Date.parse(Time.at(date.to_time.to_i + gigaseconds).to_s)
		end

		private

		def gigaseconds
			1000000000
		end

		def gigaseconds_as_days
			gigaseconds/86400
		end
	end
end
