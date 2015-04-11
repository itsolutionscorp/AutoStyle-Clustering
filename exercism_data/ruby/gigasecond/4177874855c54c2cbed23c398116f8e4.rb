require('date')

class Gigasecond
	def self.from d
#		d.kind_of? Time ? d. : d.to_date + ((10**9) / 60 / 60 / 24) 
		(d.to_time + 10**9).to_date
	end
end
