class School
	def initialize
		@students = Hash.new { |students,grade| students[grade] = []}
	end

	def add(name,grade)
		@students[grade] << name
	end

	def grade(grade)
		@students[grade].sort
	end

	def to_hash
		sorted = @students.map { |grade,names| [grade, names.sort]  }.sort
		Hash[sorted]
	end
end
