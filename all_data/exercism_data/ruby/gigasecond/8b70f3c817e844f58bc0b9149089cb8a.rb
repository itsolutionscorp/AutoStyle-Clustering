class Gigasecond
	def self.from (fromdate)
		if fromdate.respond_to?(:hour)
			fromtime = Time.new(fromdate.year, fromdate.month, fromdate.day, fromdate.hour, fromdate.min, fromdate.sec)
		else 
			fromtime = Time.new(fromdate.year, fromdate.month, fromdate.day)
		end
		gs = fromtime + 10**9
		returndate = Date.new(gs.year, gs.month, gs.day)
	end
end
