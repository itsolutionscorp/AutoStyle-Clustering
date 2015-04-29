class School

	def initialize
		@school = Hash.new([])
	end

	def to_hash
		@school.sort.to_h
	end
	
	def add(name, grade)
		@school[grade] = (@school[grade] + [name]).sort
	end
	
	def grade(num)
		@school[num].sort
	end
end
