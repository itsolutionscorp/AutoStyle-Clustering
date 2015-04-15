class School

	def initialize
		@school = {}	
	end

	def add(name, grade)
		unless @school[grade]
			@school.merge!(grade => [name])
		else 
			@school[grade].push(name)
			@school[grade].sort!
		end
	end
	
	def grade(number)
		if @school[number]
			@school[number]	
		else []
		end
	end

	def to_hash
		hash = {}
		keys = @school.keys.sort!
		keys.each do |key|
			hash.merge!(key => @school[key])
		end
		hash
	end
  
end
