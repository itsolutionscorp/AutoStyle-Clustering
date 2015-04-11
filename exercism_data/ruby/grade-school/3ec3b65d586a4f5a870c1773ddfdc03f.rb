class School

	def initialize
		@school = Hash.new([])
	end

	def add(name, grade)
		@school[grade] << name
		@school[grade].sort!
	end

	def grade(year)
		@school[year]
	end

	def to_hash
		Hash[@school.sort]
	end

end
