class School

	attr_accessor :db

	def initialize
		@db =  Hash.new
	end

	def add(name, grade)
		if not name.is_a?String
			raise ArgumentError, "name must be a string"
		elsif not grade.is_a?Integer
			raise ArgumentError, "grade must be an integer"
		end

		if not @db.key?grade
			@db[grade] = Array.new 
		end
		@db[grade].push(name)
	end

	def grade(grade)
		if not @db.key?grade
			return []
		end

		return @db[grade]
	end

	def sort
		sorted = @db
		sorted.each_key do |key|
			sorted[key].sort!
	  	end
		return Hash[sorted.sort]

	end

end
