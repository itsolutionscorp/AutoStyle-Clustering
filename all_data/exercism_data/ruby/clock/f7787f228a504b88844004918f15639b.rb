class Clock
	include Comparable
	attr_reader :hour, :minute
	
	def initialize(hour, minute)
		@hour = hour
		@minute = minute
	end
	
	def self.at(hour, minute=0)
		Clock.new(hour, minute)
	end
	
	def to_s
		if @minute > 59
			@minute -= 60
			@hour += 1
		elsif @minute < 0 && @minute >-60
			@minute += 60
			@hour -= 1
		elsif @minute < -60 
			@minute += 120
			@hour -= 2
		end
		
		@hour = 0 if @hour ==24
		@hour = 23 if @hour == -1
		
		if @hour < 10
			hours = "0#{@hour}" 
		else
			hours = @hour 
		end
		
		if @minute < 10
			minutes = "0#{@minute}"
		else
			minutes = @minute 
		end
		"#{hours}:#{minutes}"
	end
		
	def +(number)
		@minute += number
		self.to_s
	end 
		
	def -(number)
		@minute -= number
		self.to_s
	end
	
	def <=>(other)
		comparison = hour <=> other.hour 
		minute <=> other.minute if comparison == 0
	end
end
