require 'date'
require 'time'

class Gigasecond

	def  initialize (datetime)
		gs_in_days=(10**9)/60/60/24	
		@date=datetime+gs_in_days
		
	end

	attr_accessor :date
end


