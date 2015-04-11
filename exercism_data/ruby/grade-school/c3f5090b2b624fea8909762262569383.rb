class School
	def initialize(school)
		@school = school
		@students = Hash.new
	end

	def add(student, grade)
		self.student = student
		self.grade = grade
	end

	def db
		@students
	end

	def add(student, grade)
		if @students[grade] 
			@students[grade].push(student)
		else 
			@students[grade] = [student]
		end
	end

	def grade(num)
		if @students[num].nil? 
			return []
		else
			@students[num]
		end
	end

	def sort
		Hash[@students.each {|k,v| v.sort!}.sort]
	end
end
