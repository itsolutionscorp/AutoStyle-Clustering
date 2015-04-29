class School

	def initialize
		@students = {}
	end

	def add name, grade
		@students[grade] ||= []
		@students[grade] << name
		@students[grade].sort!
	end

	def to_hash
		Hash[@students.sort]
	end

	def grade grade
		@students[grade] || []
	end
end
