class Gigasecond
	def self.from time
		Time.at( time.to_i + 1000000000 )
	end
end
