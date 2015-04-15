class School

	def initialize
		@students = {}
	end

	def add name, grade
		@students[grade] ? @students[grade] << name : @students[grade] = [name]
		@students = _sort(@students)
	end

	def to_hash
		@students
	end

	def grade grade
		@students[grade] || []
	end

	def _sort students
		Hash[@students.map {|grade, names|
			{grade => names.sort}
		}.inject(&:merge).sort]
	end

end
