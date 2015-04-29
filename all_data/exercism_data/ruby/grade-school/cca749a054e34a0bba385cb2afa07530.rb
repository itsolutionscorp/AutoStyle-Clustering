class	School
	attr_reader :db, :name
	def initialize(name)
		@name = name
		@db = Hash.new {|hash,key| hash[key]=[]}
	end

	def add(student, grade_number)
		db[grade_number] << student
	end

	def grade(grade_number)
		db[grade_number]
	end

	def sort
		db.each_value { |value| value.sort! }
		Hash[db.sort]
	end
end
