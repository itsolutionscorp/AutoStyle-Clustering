class School
	
	def initialize
		@result = Hash.new
	end
	
	def to_hash
		@result
	end
	
	def add(string, number)
		if @result.has_key?(number)
			@result[number] << string
		else
			@result[number] = [string] 
		end
		@result[number].sort!
		@result = Hash[@result.sort_by{ |a,b|a }]
	end
	
	def grade(number)
		return [] unless @result.has_key?(number)
		@result[number]
	end
	
end
