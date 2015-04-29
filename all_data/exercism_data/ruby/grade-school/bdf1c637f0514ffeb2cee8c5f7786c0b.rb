class School
	def initialize
		@schooldb = {}
	end

	def to_hash
		@schooldb
	end

	def add(student, grade)
		@schooldb[grade] = [] if !@schooldb[grade]
		@schooldb[grade] << student
	end

	def grade
		@schooldb[grade]
	end
end
