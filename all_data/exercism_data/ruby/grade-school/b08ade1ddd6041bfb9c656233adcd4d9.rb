class School
	attr_accessor :db

	def initialize
		@db = Hash.new {|hash,key| hash[key] = [] }
	end
	
	def add(name, grade)
		db[grade] << name
		grade(grade)
	end

	def grade(grade)
		db[grade]
	end

	def sort
		sorted(db.keys)
	end

	def sorted(grades)
		grades.sort.inject({}) do |hash, grade|
			hash[grade] = alphabetised_students(grade)
			hash
		end
	end

	def alphabetised_students(grade)
		db[grade].sort {|x,y| x <=> y}
	end
end
