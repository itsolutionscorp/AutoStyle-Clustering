class School
	def initialize
		@students = {}
	end

	def to_hash
		Hash[@students.sort]
	end

	def add(name, grade)
		if @students.has_key?(grade)
			@students[grade] << name
			@students[grade].sort!
		else
			@students[grade] = [name]
		end
	end

	def grade(grade)
		if @students.keys.include?(grade)
			@students[grade]
		else
			[]
		end
	end
end
