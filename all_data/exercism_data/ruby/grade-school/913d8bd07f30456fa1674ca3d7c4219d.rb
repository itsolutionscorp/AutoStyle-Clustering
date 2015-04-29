class School

	def initialize
		@students = {}
	end

	def to_hash
		@students.keys.sort.each_with_object({}) do |grade, hash|
			hash[grade] = @students[grade].sort
		end
	end

	def add(name, grade)
		@students[grade] = @students[grade] || []
		@students[grade].push(name)
	end

	def grade(grade)
		(@students[grade] || []).sort
	end
	
end
