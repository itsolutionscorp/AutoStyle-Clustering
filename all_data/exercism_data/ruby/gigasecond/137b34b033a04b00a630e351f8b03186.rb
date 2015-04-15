class Gigasecond

	GIGASECOND = 10**9
	
	attr_accessor :date
	
	def initialize(init_dt)
		@init_dt = init_dt.to_time
		@date = (@init_dt + GIGASECOND).to_date
	end
	
end
