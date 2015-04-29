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
    Hash[db.sort.map {|grade, name| [grade, name.sort]}]
	end
end
