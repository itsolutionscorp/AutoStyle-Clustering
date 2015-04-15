class School
	attr_accessor :db

	def initialize
		@db = {}
	end
	
	def add(name, grade)
		db[grade] = [] unless db[grade]
		db[grade] << name

		grade(grade)
	end

	def grade(grade)
		db[grade] || []
	end

	def sort
		sorted = {}
		grades = db.keys
		grades.sort.each do |grade|
			sorted[grade] = db[grade].sort {|x,y| x <=> y}
		end
		sorted
	end
end
