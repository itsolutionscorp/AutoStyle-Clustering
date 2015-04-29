class Gigasecond 
	attr_accessor :cur_date
	def initialize(date)
    	@cur_date = date
  	end
	def date
		Date.parse(Time.at(cur_date.to_time.to_i + (10**9)).to_s)
	end	
end
