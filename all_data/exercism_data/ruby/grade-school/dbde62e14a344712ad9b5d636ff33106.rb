class School
	def initialize
		@schooldb = Hash.new{|k,v| k[v]=[]}
	end

	def to_hash
		@schooldb.sort.to_h
	end

	def add(student, grade)
		@schooldb[grade] << student
		@schooldb[grade].sort!
	end

	def grade(g)
		@schooldb[g]
	end
end
