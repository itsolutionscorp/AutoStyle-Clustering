class Gigasecond
	def self.from(arg)
		Time.at(10**9 + arg.strftime('%s').to_i).to_date
	end
end
