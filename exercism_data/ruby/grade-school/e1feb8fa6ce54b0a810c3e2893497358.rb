class School
	def initialize
		@roster = {}
	end

	def db
		@roster
	end

	def add(student, grade)
		current_students = @roster[grade]

		if current_students
			current_students << student
		else
			current_students = [student]
		end

		@roster[grade] = current_students
	end

	def grade(grade)
		@roster[grade] ? @roster[grade] : []
	end

	def sort 
		ordered = {}
		@roster.sort.each do |key, value| 
			ordered[key] = @roster[key]
			ordered[key] = value.sort
		end

		ordered
	end
end
