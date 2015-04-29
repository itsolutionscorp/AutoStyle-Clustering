class Gigasecond 
	attr_accessor :cur_date
	def initialize(date)
    	@cur_date = date
  	end
	def date
		(cur_date.to_time + (10**9)).to_date
	end	
end
